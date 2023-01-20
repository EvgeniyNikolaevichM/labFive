import MyExeptions.DuplicateModelNameException;
import MyExeptions.NoSuchModelNameException;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws DuplicateModelNameException, NoSuchModelNameException, IOException, ClassNotFoundException {
//        System.out.println("Создаем 3 класса Vehicle");
//        Auto auto = new Auto("Audi", 5);
/*        System.out.println(auto);
        Auto auto1 = new Auto("Audi",2);
        System.out.println(auto1);*/
        //Задание 6
/*        Vehicle auto0 = new Auto("Pot",3);
        System.out.println(auto);
        Vehicle auto1 = new Auto("Pot",3);
        System.out.println(auto1);
        Vehicle auto2 = new Auto("Pot",3);
        System.out.println(auto2);
        System.out.println("Средняя цена за все автомобили в сумме " + VehicleStaticClass.getVehicleAveragePrices(auto0,auto1,auto2));*/
/*        FileWriter fileWriter = new FileWriter("data.txt");
        // Задание 7
        VehicleStaticClass.writesVehicle(auto,fileWriter);
        VehicleStaticClass.readsVehicle();*/
/*        System.out.println("Количество моделей в массиве равно = " + auto.getSizeModelArray());
        VehicleStaticClass.printModels(auto);
        System.out.println("Распечатываем цены");
        VehicleStaticClass.printPrices(auto);*/
/*        System.out.println("Создаем 3 модели мотоциклов");
        Vehicle motorcycle = new Motorcycle("Suzuku",2);
        System.out.println("Количество моделей в массиве равно = " + motorcycle.getSizeModelArray());
        VehicleStaticClass.printModels(motorcycle);
        System.out.println("Распечатываем цены");
        VehicleStaticClass.printPrices(motorcycle);*/
//        System.out.println("Добавляем 4 модель");
//        auto.addModel("Suzuki",303.0);
//        System.out.println("Распечатываем модели с добавленной моделью");
//        VehicleStaticClass.printModels(auto);
//        System.out.println("Распечатываем цены");
        //       VehicleStaticClass.printPrices(auto);
        //      System.out.println("Обновляем название модели Vaz0");
        //      auto.setModelName("Vaz0","Audi");
        //      System.out.println("Распечатываем модели с измененной моделью");
//        VehicleStaticClass.printModels(auto);
//        System.out.println("Распечатываем названия"+ Arrays.toString(auto.getAllModelNames()));
//        System.out.println("Получаем цену у Audi " + auto.getPriceModelByName("Audi"));
//        System.out.println("Удаляем модель Audi");
//        auto.deleteModel("Audi");
//        System.out.println("Распечатываем модели с удаленной моделью");
//        VehicleStaticClass.printModels(auto);
//        System.out.println("Меняем цену у Suzuki с 303 на 404 ");
//        auto.setPriceModelByName("Suzuki",404.0);
//        System.out.println("Распечатываем цены ");
//        VehicleStaticClass.printPrices(auto);
/*        System.out.println("Сохраним список моделей в файл");
        FileOutputStream fos = new FileOutputStream("data.dat");
        VehicleStaticClass.outputVehicle(auto,fos);
        fos.close();
        FileInputStream fis = new FileInputStream("data.dat");
        Vehicle vehicleresult = VehicleStaticClass.inputVehicle(fis);
        System.out.println(vehicleresult.getMark());
        System.out.println(vehicleresult.getSizeModelArray());
        System.out.println(vehicleresult);*/
        /*VehicleStaticClass.printModels(vehicleresult);
        System.out.println("Распечатываем цены");
        VehicleStaticClass.printPrices(vehicleresult);
        System.out.println("Сохраним список моделей в файл");
        File file = new File("data.byte");
        long timestamp = file.lastModified();
        System.out.println("data.byte последний раз был изменен = " + new Date(timestamp));
        FileWriter fileWriter = new FileWriter("data.txt");
        VehicleStaticClass.writeVehicle(auto, fileWriter);
        fileWriter.flush();
        FileReader fileReader = new FileReader("data.txt");
        Vehicle vehicleresult2 = VehicleStaticClass.readVehicle(fileReader);
        System.out.println(vehicleresult2.getSizeModelArray());
        VehicleStaticClass.printModels(vehicleresult2);
        System.out.println("Распечатываем цены");
        VehicleStaticClass.printPrices(vehicleresult2);*/
/*        Auto auto = new Auto("AIDI",2);
        FileOutputStream fos = new FileOutputStream("auto10.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(auto);
        oos.close();
        System.out.println("Файл записан");
        FileInputStream fis = new FileInputStream("auto10.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Vehicle motor = (Vehicle) ois.readObject();
        System.out.println("Вывод информации о машинах...");
        System.out.println(motor);
        ois.close();*/
        // лабораторная 4
        // консольный ввод
/*        System.out.println("Введите Auto or Motorcycle");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String nameOfClass = reader.readLine();
        System.out.println("Введите модель выбранного транспортного средства");
        String nameOfModel = reader.readLine();
        System.out.println("Введите число моделей");
        int numberOfModel = Integer.parseInt(reader.readLine());
        Vehicle auto = null;
        if (Objects.equals(nameOfClass, "Auto"))
            auto = new Auto(nameOfModel, numberOfModel);
        else auto = new Motorcycle(nameOfModel,numberOfModel);
        System.out.println("Количество моделей в массиве равно = " + auto.getSizeModelArray());
        VehicleStaticClass.printModels(auto);
        System.out.println("Распечатываем цены");
        VehicleStaticClass.printPrices(auto);*/
/*        System.out.println(auto.equals(auto1));
        Vehicle vehicleres = VehicleStaticClass.readVehicle(new InputStreamReader(System.in));
        VehicleStaticClass.writeVehicle(vehicleres,new OutputStreamWriter(System.out));*/
/*        System.out.println("Создадим файл cars.dat");
        Path path = Paths.get("cars.dat");
        try (ObjectOutputStream oos = new ObjectOutputStream(
                Files.newOutputStream(path))) {
            Vehicle auto3 = new Auto("Loo", 4);
            oos.writeObject(auto3);
            System.out.println("Созданный vehicle "+"\n" + auto3);
        }
        try (ObjectInputStream ois = new ObjectInputStream(
                Files.newInputStream(path))) {
            Vehicle read = (Auto) ois.readObject();
            System.out.printf("Считанный vehicle: %s", read);
        }*/
/*        System.out.println("Сравнивaем объекты класса Auto и Auto " + auto.equals(auto1));
        System.out.println("Изменим цену у первой модели с 200 на 300");
        auto1.setPriceModelByName("Audi0",300);
        System.out.println(auto1);
        System.out.println("Сравним " + auto.equals(auto1));*/
/*        System.out.println("Сравнивaем объекты класса Motorcycle и Motorcycle " + auto.equals(auto1));
        System.out.println("Изменим цену у первой модели с 200 на 400");
        auto1.setModelName("Pot0","400");
        System.out.println(auto1);
        System.out.println("Сравнивaем объекты класса Motorcycle и Motorcycle " + auto.equals(auto1));*/

//======================================================================================================================



//        Auto auto = new Auto("Skoda", 2);
//        System.out.println(auto);
//        if (args.length == 4) {
//            try {
//                Class<?> cl = Class.forName(args[0]); //По имени класса получаем ссылку на класс класса(Получили объект класса по его названию)
//                Class<?>[] param = new Class[]{String.class, double.class}; //Создаем массив типа класс и типы стринг и дабл(Массив параметров)
//                Method setPriceModelByNameMethod = cl.getMethod(args[1], param);//Получаем ссыдку на метод с именем и параметрами стринг и дабл
//                System.out.println(setPriceModelByNameMethod); //печатаем
//                setPriceModelByNameMethod.invoke(auto, args[2], Double.parseDouble(args[3]));//вызываем, у объекта авто имя и цена парс-стринг в дабл преобразование
//                System.out.println("Change: " + auto);//выводим измененное значение
//            } catch (ClassNotFoundException | NoSuchMethodException e) {
//                throw new RuntimeException(e);
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println(Objects.requireNonNull(VehicleStaticClass.createVehicle(auto, "Solo", 2)).getClass());



//        Auto auto = new Auto("Skoda", 2);     //Создание по образцу
//        Motorcycle moto = new Motorcycle("Suzuki", 2);
//        Vehicle newCar = VehicleStaticClass.createVehicle(auto, "NewAuto", 2);
//        Vehicle newMoto = VehicleStaticClass.createVehicle(moto, "NewAuto", 2);
//        System.out.println(newCar.getClass());
//        System.out.println(newMoto.getClass());



//        Vehicle Scooter = new Scooter("TheBest",5);
//        System.out.println(Arrays.toString(Scooter.getAllModelNames()));
//        System.out.println(Arrays.toString(Scooter.getAllModelPrices()));
//        System.out.println("Price TheBest0 " + Scooter.getPriceModelByName("TheBest0"));
//        System.out.println("add Model");
//        Scooter.addModel("TheBad",100);
//        System.out.println(Scooter);
//        System.out.println("Change Price TheBad ");
//        Scooter.setPriceModelByName("TheBad",9999999);
//        System.out.println("Change name TheBad to The mustBad");
//        Scooter.setModelName("TheBad","mustBad");
//        System.out.println(Scooter);
//        System.out.println("Del mustBad ");
//        Scooter.deleteModel("mustBad");
//        System.out.println(Scooter);



//        QuadBike quadBike = new QuadBike("TheBest",5);
//        System.out.println(quadBike);
//        quadBike.deleteModel("TheBest4");
//        System.out.println(quadBike);
//        quadBike.setModelName("TheBest0","TheBestTheBest");
//        System.out.println(quadBike);
//        System.out.println(Arrays.toString(quadBike.getAllModelNames()));
//        System.out.println(quadBike.getPriceModelByName("TheBestTheBest"));
//        quadBike.setPriceModelByName("TheBestTheBest",1000);
//        System.out.println(quadBike);
//        System.out.println(Arrays.toString(quadBike.getAllModelPrices()));
//        quadBike.addModel("TheBad",1);
//        System.out.println(quadBike);



//        Moped moped = new Moped("TheBest",2);
//        System.out.println(moped);
//        moped.deleteModel("TheBest1");
//        System.out.println(moped);
//        moped.addModel("TheBest1",1);
//        System.out.println(moped);
//        moped.setModelName("TheBest1","TheBest11");
//        System.out.println(moped);
//        System.out.println(Arrays.toString(moped.getAllModelNames()));
//        System.out.println(moped.getPriceModelByName("TheBest11"));
//        moped.setPriceModelByName("TheBest11",1000);
//        System.out.println(moped);
//        System.out.println(Arrays.toString(moped.getAllModelPrices()));



//        Scooter Scooter = new Scooter("Scoot", 2);
//        QuadBike QuadBike = new QuadBike("Bike", 2);
//        Moped Moped = new Moped("Mot", 2);
//        System.out.println(Scooter);
//        System.out.println(QuadBike);
//        System.out.println(Moped);
//        double aver = VehicleStaticClass.getVehicleAveragePrices(Scooter, QuadBike, Moped);
//        System.out.println(aver);



//        System.out.println("=====in console write: Class, Mark, Len, Models, Price=====");
//        Vehicle transportConsole =  VehicleStaticClass.readsVehicle(new InputStreamReader(System.in));
//        System.out.println("=====You add:=====");
//        VehicleStaticClass.writesVehicle((Vehicle) transportConsole, new OutputStreamWriter(System.out));
    }
}