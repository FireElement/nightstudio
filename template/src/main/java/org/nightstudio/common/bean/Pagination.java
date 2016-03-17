package org.nightstudio.common.bean;

public class Pagination {
	private boolean init;
	private long rowCount;
	private int pageSize;
	private long pageCount;
	private long currPage;
	
	public Pagination (int pageSize) {
		this.init = false;
		this.rowCount = 0;
		this.pageSize = pageSize;
		this.pageCount = 0;
		this.currPage = 0;
	}
	
	public void init (long rowCount) {
		this.rowCount = rowCount;
		this.pageCount = (this.rowCount + this.pageSize - 1) / this.pageSize;
		this.currPage = (this.pageCount != 0)?1:0;
		this.init = true;
	}

	public long getRowCount() {
		return rowCount;
	}

	public void setRowCount(long rowCount) {
		this.init(rowCount);
	}

	public int getPageSize() {
		return pageSize;
	}

	public long getPageCount() {
		return pageCount;
	}

	public long getCurrPage() {
		return currPage;
	}

	public void setCurrPage(long currPage) {
		if (currPage >= 0 && currPage <= this.pageCount) {
			this.currPage = currPage;
		}
	}

	public boolean isInit() {
		return init;
	}
	
	public long getFirstResult() {
		if (this.pageCount == 0) {
			return 0;
		}
		long result = (this.currPage - 1) * this.pageSize + 1;
		result--;
		return result;
	}
}
