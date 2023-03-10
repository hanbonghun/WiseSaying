package org.example.wiseSaying.controller;
import org.example.Container;
import org.example.Rq;
import org.example.wiseSaying.entity.WiseSaying;
import org.example.wiseSaying.service.WiseSayingService;

import java.util.List;

public class WiseSayingController {
    private final WiseSayingService wiseSayingService;

    public WiseSayingController(  ){
        wiseSayingService = new WiseSayingService();
    }
    public void write() {
        System.out.print("명언 : ");
        String content = Container.getScanner().nextLine().trim();
        System.out.print("작가 : ");
        String author = Container.getScanner().nextLine().trim();

        //서비스에는 고객과 상호작용 하는 부분이 있으면 안됨
        long id = wiseSayingService.write(content,author);
        System.out.printf("%d번 명언이 등록되었습니다.\n",id);

    }

    public void list() {
        List<WiseSaying>wiseSayingList = wiseSayingService.findAll();

        System.out.println("번호 / 작가 / 명언");
        System.out.println("---------------");

        for (int i = wiseSayingList.size()-1; i >=0; i--) {
            System.out.println(wiseSayingList.get(i));
        }
    }

    public void remove( Rq rq ) {
        long id = rq.getLongParam("id",-1);
        if(id==-1){
            System.out.println("id(정수)를 입력하세요.");
            return;
        }
        WiseSaying wiseSaying = wiseSayingService.findById(id);
        if(wiseSaying==null){
            System.out.printf("%d번 명언은 존재하지 않습니다.\n",id);
            return;
        }
        wiseSayingService.remove(wiseSaying);
        System.out.printf("%d번 명언이 삭제되었습니다.\n",id);
    }

    public void modify(Rq rq) {
        long id = rq.getLongParam("id",-1);
        if(id==-1){
            System.out.println("id(정수)를 입력하세요.");
            return;
        }
        WiseSaying wiseSaying = wiseSayingService.findById(id);
        if(wiseSaying==null){
            System.out.printf("%d번 명언은 존재하지 않습니다.\n",id);
            return;
        }
        System.out.printf("명언(기존) : %s\n",wiseSaying.getContent());
        System.out.printf("작가(기존) : %s\n",wiseSaying.getAuthorName());
        System.out.print("명언 : ");
        String content = Container.getScanner().nextLine().trim();
        System.out.print("작가 : ");
        String author = Container.getScanner().nextLine().trim();
        wiseSaying.setContent(content);
        wiseSaying.setAuthorName(author);
        wiseSayingService.modify(wiseSaying, content, author);
        System.out.printf("%d번 명언이 수정되었습니다.\n",id);
    }

    public void build() {
        wiseSayingService.build();
        System.out.println("data.json 파일의 내용이 갱신되었습니다.");
    }
}
