package hello.core.singleton;

public class StatefulService {

//    private int price; //상태를 유지하는 빌드
//공유 필드는 항상 조심, 스프링 빈은 항상 무상태로 설계하자
    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        return price;
    }

//    public int getPrice() {       // order에서 리턴값을 주면서 이 메서드는 사용X
//        return price;
//    }
}
