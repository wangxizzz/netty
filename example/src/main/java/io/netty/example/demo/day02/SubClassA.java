package io.netty.example.demo.day02;

/**
 * <Description>
 *
 * @author wangxi
 */
public class SubClassA extends AbstractA{
    @Override
    public void say() {
        System.out.println("SubClassA的say()");
    }

    public static void main(String[] args) {
        SubClassA subClassA = new SubClassA();
    }
}

