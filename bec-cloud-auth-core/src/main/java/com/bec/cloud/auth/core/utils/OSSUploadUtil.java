package com.bec.cloud.auth.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.GenericRequest;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PolicyConditions;
import com.bec.cloud.auth.core.properties.SecurityProperties;


/**
 * 
 * @date 2018年4月8日上午11:19:10
 * @author suruiliang
 *
 */
@Component
public class OSSUploadUtil implements InitializingBean {
	private static Logger logger = LoggerFactory.getLogger(OSSUploadUtil.class);
	private static String ENDPOINT;
	private static String ACCESS_KEY_ID;
	private static String ACCESS_KEY_SECRET;
	public static String BUCKET;
	public static String DIR;
	private static Long EXPIRE_TIME;
	@Autowired
	private SecurityProperties securityProperties=new SecurityProperties();
	
	public OSSUploadUtil() {
		ENDPOINT =securityProperties.getOss().getEndpoint();
		ACCESS_KEY_ID =securityProperties.getOss().getAccessKeyId();
		ACCESS_KEY_SECRET =securityProperties.getOss().getAccessKeySecret();
		BUCKET=securityProperties.getOss().getBucket();
		DIR=securityProperties.getOss().getDir();
		EXPIRE_TIME=securityProperties.getOss().getExpireIn();
	}


	@Override
	public void afterPropertiesSet() throws Exception {
		ENDPOINT =securityProperties.getOss().getEndpoint();
		ACCESS_KEY_ID =securityProperties.getOss().getAccessKeyId();
		ACCESS_KEY_SECRET =securityProperties.getOss().getAccessKeySecret();
		BUCKET=securityProperties.getOss().getBucket();
		DIR=securityProperties.getOss().getDir();
		EXPIRE_TIME=securityProperties.getOss().getExpireIn();
	}


	@SuppressWarnings("deprecation")
	private static OSSClient getOSSClient(){
		logger.trace("获得OSS Client");
		return new OSSClient(ENDPOINT,ACCESS_KEY_ID, ACCESS_KEY_SECRET);
	}

	public static Map<String, String> objectPolicy() throws UnsupportedEncodingException {
		String host = "http://" + BUCKET + "." + ENDPOINT;
		OSSClient client = getOSSClient();
		long expireEndTime = System.currentTimeMillis() + EXPIRE_TIME * 1000;
		Date expiration = new Date(expireEndTime);
		PolicyConditions policyConds = new PolicyConditions();
		policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
		policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, DIR);

		String postPolicy = client.generatePostPolicy(expiration, policyConds);
		byte[] binaryData = postPolicy.getBytes("utf-8");
		String encodedPolicy = BinaryUtil.toBase64String(binaryData);
		String postSignature = client.calculatePostSignature(postPolicy);

		Map<String, String> respMap = new LinkedHashMap<String, String>();
		respMap.put("accessid", ACCESS_KEY_ID);
		respMap.put("policy", encodedPolicy);
		respMap.put("signature", postSignature);
		respMap.put("dir", DIR);
		respMap.put("host", host);
		respMap.put("expire", String.valueOf(expireEndTime / 1000));
		return respMap;

	}
	public static boolean createBucket(String bucketName){
		OSSClient client=getOSSClient();
		if(!client.doesBucketExist(bucketName)){
			CreateBucketRequest createBucketRequest= new CreateBucketRequest(bucketName);
			// 设置bucket权限 默认私有
			//createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
			createBucketRequest.setCannedACL(CannedAccessControlList.PublicReadWrite);

			Bucket bucket = client.createBucket(createBucketRequest);
			logger.trace("创建 Bucket");
			return bucketName.equals(bucket.getName());
		}
		return true;
	}

	public static void deleteBucket(String bucketName){
		getOSSClient().deleteBucket(bucketName);
		logger.trace("删除" + bucketName + "Bucket成功");
	}

	private static String getBucketName(String fileUrl){
		String http = "http://",https = "https://";
		if(fileUrl.startsWith(http)||fileUrl.startsWith(https)){
			fileUrl= fileUrl.replace(https,"").replace(http,"");
		}
		return StringUtils.substringBefore(fileUrl, ".oss-");
	}

	private static String getFileName(String fileUrl){
		return StringUtils.substringAfter(fileUrl, "aliyuncs.com/");
	}


	private static List<String> getFileName(List<String> fileUrls){
		List<String> names = new ArrayList<String>();
		for (String url : fileUrls) {
			names.add(getFileName(url));
		}
		return names;
	}
	/**
	 * 向阿里云的OSS存储中存储文件  --file也可以用InputStream替代
	 * @param file 上传文件
	 * @param bucketName bucket名称
	 * @param diskName 上传文件的目录  --bucket下文件的路径
	 * @return String 唯一MD5数字签名
	 * */
	public static String uploadFile(File file, String bucketName, String diskName) {
		if(file==null){
			throw new RuntimeException("没有要上传的文件!");
		}
		return putObject(file,bucketName,diskName);
	}
	/**
	 * 向阿里云的OSS存储中存储文件  --file也可以用InputStream替代
	 * @param in 上传文件缓冲
	 * @param bucketName bucket名称
	 * @param diskName 上传文件的目录  --bucket下文件的路径
	 * @return String 唯一MD5数字签名
	 * */
	public static String uploadFile(InputStream in, String bucketName, String diskName,String fileName) {
		if(in==null){
			throw new RuntimeException("没有要上传的文件!");
		}
		return putObject(in,bucketName,diskName,fileName);
	}

	/**
	 * @MethodName: putObject
	 * @Description: 上传文件
	 * @param file
	 * @param bucketName
	 * @param diskName
	 * @return String
	 */
	private static String putObject(File file, String bucketName, String diskName) {
		if(file==null){
			throw new RuntimeException("没有要上传的文件!");
		}
		OSSClient client=getOSSClient();
		try {
			//InputStream is = new FileInputStream(file);
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(getContentType( file.getName()));
			meta.setCacheControl("no-cache");
			meta.setHeader("Pragma", "no-cache");
			meta.setContentEncoding("utf-8");
			//上传文件
			String filePath=diskName + file.getName();
			client.putObject(bucketName, filePath, new FileInputStream(file), meta);
			//上传成功再返回的文件路径
			if (ENDPOINT.startsWith("http://")) {
				return ENDPOINT.replaceFirst("http://","http://"+bucketName+".")+"/"+filePath;
			}else if (ENDPOINT.startsWith("https://")) {
				return ENDPOINT.replaceFirst("https://","https://"+bucketName+".")+"/"+filePath;
			}else{
				return "http://"+bucketName+"."+ENDPOINT+"/"+filePath;
			}
		} catch (FileNotFoundException e) {
			logger.error("未找到文件." + e.getMessage());
			throw new RuntimeException("没有要上传的文件!",e);
		} catch (Exception e) {
			logger.error("上传阿里云OSS服务器异常." + e.getMessage());
			throw new RuntimeException("文件上传异常!",e);
		} finally {
			client.shutdown();
		}
	}
	/**
	 * @MethodName: putObject
	 * @Description: 上传文件
	 * @param in
	 * @param bucketName
	 * @param diskName
	 * @param fileName
	 * @return String
	 */
	private static String putObject(InputStream in,String bucketName, String diskName,String fileName) {
		if(in==null){
			throw new RuntimeException("没有要上传的文件!");
		}
		OSSClient client=getOSSClient();
		try {
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(getContentType(fileName));
			meta.setCacheControl("no-cache");
			meta.setHeader("Pragma", "no-cache");
			meta.setContentEncoding("utf-8");
			// meta.setContentDisposition("filename/filesize=" + fileName + "/" + buffer.getBytes().length + "Byte.");
			//上传文件
			client.putObject(bucketName, diskName + fileName,in, meta);
			//上传成功再返回的文件路径
			if (ENDPOINT.startsWith("http://")) {
				return ENDPOINT.replaceFirst("http://","http://"+bucketName+".")+"/"+diskName+fileName;
			}else if (ENDPOINT.startsWith("https://")) {
				return ENDPOINT.replaceFirst("https://","https://"+bucketName+".")+"/"+diskName+fileName;
			}else{
				return "http://"+bucketName+"."+ENDPOINT+"/"+diskName+fileName;
			}
		} catch (Exception e) {
			throw new RuntimeException("文件上传失败!",e);
		} finally {
			client.shutdown();
		}
	}


	/**
	 * @MethodName: deleteFile
	 * @Description: 单文件删除
	 * @param fileUrl 需要删除的文件url
	 * @return boolean 是否删除成功
	 */
	public static boolean deleteFile(String fileUrl){
		String bucketName = getBucketName(fileUrl);       //根据url获取bucketName
		String fileName = getFileName(fileUrl);           //根据url获取fileName
		if(bucketName==null||fileName==null) return false;
		OSSClient client=getOSSClient();
		try {
			GenericRequest request = new DeleteObjectsRequest(bucketName).withKey(fileName);
			client.deleteObject(request);
		} catch (Exception e) {
			logger.error("文件删除失败." + e.getMessage());
			throw new RuntimeException("文件删除失败.",e);
		} finally {
			client.shutdown();
		}
		return true;
	}

	/**
	 * @MethodName: batchDeleteFiles
	 * @Description: 批量文件删除(较快)：适用于相同endPoint和BucketName
	 * @param fileUrls 需要删除的文件url集合
	 * @return int 成功删除的个数
	 */
	public static int deleteFile(List<String> fileUrls){
		int deleteCount = 0;    //成功删除的个数
		String bucketName = getBucketName(fileUrls.get(0));       //根据url获取bucketName
		List<String> fileNames = getFileName(fileUrls);         //根据url获取fileName
		if(bucketName==null||fileNames.size()<=0) return 0;
		OSSClient client=getOSSClient();
		try {
			DeleteObjectsRequest request = new DeleteObjectsRequest(bucketName).withKeys(fileNames);
			DeleteObjectsResult result = client.deleteObjects(request);
			deleteCount = result.getDeletedObjects().size();
		} catch (Exception e) {
			logger.error("删除失败." + e.getMessage());
			throw new RuntimeException("文件删除失败.",e);
		} finally {
			client.shutdown();
		}
		return deleteCount;
	}

	/**
	 *
	 * @MethodName: batchDeleteFiles
	 * @Description: 批量文件删除(较慢)：适用于不同endPoint和BucketName
	 * @param fileUrls 需要删除的文件url集合
	 * @return int 成功删除的个数
	 */
	public static int deleteFiles(List<String> fileUrls) {
		int count = 0;
		for (String url : fileUrls) {
			if(deleteFile(url)){
				count++;
			}
		}
		return count;
	}

	/**
	 * 根据key获取OSS服务器上的文件输入流
	 * @param bucketName bucket名称
	 * @param diskName 文件路径
	 * @param key Bucket下的文件的路径名+文件名
	 */
	public static final InputStream getOSSInputStream(String bucketName, String diskName, String key){
		OSSClient client=getOSSClient();
		OSSObject ossObj = client.getObject(bucketName, diskName + key);
		InputStream inputStream=ossObj.getObjectContent();
		client.shutdown();
		return inputStream;
	}
	/**
	 * 通过文件名判断并获取OSS服务文件上传时文件的contentType
	 * @param fileName 文件名
	 * @return 文件的contentType
	 */
	private static String getContentType(String fileName){
		String fileExtension = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
		String contentType = "";
		switch (fileExtension) {
		case "bmp":
			contentType = "image/bmp";
			break;
		case "gif":
			contentType = "image/gif";
			break;
		case "png":
		case "jpeg":
		case "jpg":
			contentType = "image/jpeg";
			break;
		case "html":
			contentType = "text/html";
			break;
		case "txt":
			contentType = "text/plain";
			break;
		case "vsd":
			contentType = "application/vnd.visio";
			break;
		case "ppt":
		case "pptx":
			contentType = "application/vnd.ms-powerpoint";
			break;
		case "doc":
		case "docx":
			contentType = "application/msword";
			break;
		case "xml":
			contentType = "text/xml";
			break;
		case "mp4":
			contentType = "video/mp4";
			break;
		default:
			contentType = "application/octet-stream";
			break;
		}
		return contentType;
	}

	//public static void main(String[] args) {
		//String bucket="bec-test";
		//创建 Bucket
		//createBucket("bec-test");
		//删除 Bucket
		//deleteBucket("test-rayeye");

		//logger.error(uploadFile(new File("C:\\Users\\surui\\Desktop\\框架\\ff\\assets\\img\\user4.png"),bucket,"file/img/"));
		//logger.trace( uploadFile(new File("C:\\Users\\surui\\Desktop\\框架\\ff\\index.html"),bucket,"file/html/"));
		//logger.trace(deleteFile("https://bec-test123.oss-cn-hangzhou.aliyuncs.com/file/html/index.html")+"");

	//}

}
