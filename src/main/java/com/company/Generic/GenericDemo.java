package com.company.Generic;


/**
 * @Description GenericDemo 泛型案例
 * @Author stopping
 * @date: 2021/7/4 17:26
 */

public class GenericDemo {

    static class Pair<T>{
        T first;
        public Pair(T first) {
            this.first = first;
        }

        public T getFirst() {
            return first;
        }

        public void setFirst(T first) {
            this.first = first;
        }
    }

    static class PairV1<T,U>{
        T first;
        U second;

        public T getFirst() {
            return first;
        }

        public void setFirst(T first) {
            this.first = first;
        }

        public U getSecond() {
            return second;
        }

        public void setSecond(U second) {
            this.second = second;
        }

        public PairV1(T first, U second) {
            this.first = first;
            this.second = second;
        }
    }

    static class NumberPair<T extends Number,U extends Number> extends PairV1<T,U>{

        public NumberPair(T first, U second) {
            super(first, second);
        }

        public double sum(){
            return getFirst().doubleValue() + getSecond().doubleValue();
        }
    }

    static class PairV2{
        public <U> U sayHello(U u){
            System.out.println("sayHello:"+u);
            return u;
        }

        public static <T extends Comparable> T max(T[] arr){
            T max = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (arr[i].compareTo(max)>0){
                    max = arr[i];
                }
            }
            return max;
        }
    }

    static class PairObject{
        Object first;

        public PairObject(Object first) {
            this.first = first;
        }

        public Object getFirst() {
            return first;
        }

        public void setFirst(Object first) {
            this.first = first;
        }
    }

    public static void main(String[] args) {
        //单个泛型参数
        Pair<Integer> pair = new Pair<>(1);
        Integer first = pair.getFirst();
        //多个泛型参数
        PairV1<Integer,String> pairV1 = new PairV1<>(1,"2");
        String second = pairV1.getSecond();
        //object对比
        PairObject pairObject = new PairObject(1);
        Integer first1  = (Integer) pairObject.getFirst();
        //方法使用泛型
        PairV2 pairV2 = new PairV2();

        Integer helloReturn = pairV2.sayHello(1);
        System.out.println("helloReturn:"+helloReturn);

        //上界限制
        NumberPair<Double, Double> numberPair = new NumberPair<>(1.1,2.4);
        System.out.println(numberPair.sum());

        //上界接口
        Integer age [] = {1,2,3,4,5,111};
        System.out.println("PairV2.max(age) = "+PairV2.max(age));
    }
}
