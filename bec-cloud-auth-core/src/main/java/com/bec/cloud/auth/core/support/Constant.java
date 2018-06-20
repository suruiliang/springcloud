package com.bec.cloud.auth.core.support;

public class Constant {
	/**
	 * 是否删除
	 */
	public final static class DelStatus{
		public final static int NORMAL=0;
		public final static int DELETED=1;
	}
	/**
	 * 是否启用
	 */
	public final static class OnOffStatus{
		public final static int ON=0;
		public final static int OFF=1;
	}
	/**
	 * 是否完成
	 */
	public final static class FinishStatus{
		public final static int UNFINISH=0;
		public final static int FINISHED=1;
	}
	/**
	 * 审核状态
	 */
	public final static class TaskStatus{
		public final static int UN_AUDIT=0;
		public final static int AUDIT_ACCEPT=1;
		public final static int AUDIT_REJECTED=2;
		public final static int UN_FUYOU_AUDIT=3;
		public final static int FUYOU_AUDIT_ACCEPT=4;
		public final static int FUYOU_AUDIT_REJECTED=5;
		public final static int UN_DZF_AUDIT=6;
		public final static int DZF_AUDIT_ACCEPT=7;
		public final static int DZF_AUDIT_REJECTED=8;
	}
}
