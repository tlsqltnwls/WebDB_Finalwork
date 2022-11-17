package com.finalwork.paging;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

// 페이징, 검색
public class Criteria {
	private int currentPageNo;		// 현재 페이지 번호
	private int recordsPerPage;		// 페이지당 출력할 데이터 개수
	private int recordsPage;		
	private int pageSize;			// 화면 하단에 출력할 페이지의 사이즈
	private String searchKeyword;	// 검색 키워드
	private String searchType;		// 검색 유형
	
	public Criteria() {
		this.currentPageNo = 1;
		this.recordsPerPage = 10;
		this.pageSize = 10;
	}
	
	public int getStartPage() {
		return (currentPageNo - 1) * recordsPerPage;
	}
	
	public String makeQueryString(int pageNo) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("currentPageNo", pageNo)
				.queryParam("recordsPerPage", recordsPerPage)
				.queryParam("pageSize", pageSize)
				.queryParam("searchType", searchType)
				.queryParam("searchKeyword", searchKeyword)
				.build().encode();
		return uriComponents.toUriString();
	}

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

	public int getRecordsPage() {
		return recordsPage;
	}

	public void setRecordsPage(int recordsPage) {
		this.recordsPage = recordsPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	@Override
	public String toString() {
		return "Criteria [currentPageNo=" + currentPageNo + ", recordsPerPage=" + recordsPerPage + ", recordsPage="
				+ recordsPage + ", pageSize=" + pageSize + ", searchKeyword=" + searchKeyword + ", searchType="
				+ searchType + "]";
	}
}
