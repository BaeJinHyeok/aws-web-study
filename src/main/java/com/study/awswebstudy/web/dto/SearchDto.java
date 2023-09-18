//package com.study.awswebstudy.web.dto;
//
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@NoArgsConstructor
//@Getter
//public class SearchDto {
//
//    private int page; // 현재 페이지 번호
//    private int recordsize; //페이지 당 출력할 데이터 개수
//    private int pagesize; // 화면 하단에 출력할 페이지 크기를 의미 5 -> 1~5
//    private String keyword; //검색 키워드를 의미
//    private String searchType; // 검색 유형을 의미
//
//    @Builder
//    public SearchDto(int page, int recordsize, int pagesize){
//        this.page = page;
//        this.recordsize = recordsize ;
//        this.pagesize = pagesize ;
//    }
//
//    public int getOffSet(){
//        return (page -1) * recordsize;
//    }
//}
