package gateway.config;

/**
 * @Author yh
 * @Date 2020/9/7 20:04
 */
public class Test {

  public static void main(String[] args) {
    new TestInterface(){
      @Override
      public void test() {
        System.out.println(11111);
      }
    }.test();
  }
}
