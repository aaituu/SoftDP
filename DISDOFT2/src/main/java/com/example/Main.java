    package com.example;


    import com.example.logistics.*;

    public class Main {
        public static void main(String[] args) {

//            RoadFabric
            LogisticsFactory roadFactory = new RoadLogisticsFactory();
            Transport roadTransport = roadFactory.createTransport();
            Packaging roadPackaging = roadFactory.createPackaging();

            roadTransport.deliver();
            roadPackaging.pack();

            System.out.println("--------------------");

//            SeaFabric
            LogisticsFactory seaFactory = new SeaLogisticsFactory();
            Transport seaTransport = seaFactory.createTransport();
            Packaging seaPackaging = seaFactory.createPackaging();

            seaTransport.deliver();
            seaPackaging.pack();



        }
    }
