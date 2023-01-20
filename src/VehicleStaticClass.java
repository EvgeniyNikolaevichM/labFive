import MyExeptions.DuplicateModelNameException;

import java.io.*;
import java.util.Scanner;


public class VehicleStaticClass {
    public static double averagePrice(Vehicle vehicle) {
        double result = 0;
        for (double price : vehicle.getAllModelPrices()) {
            result += price;
        }
        return result / vehicle.getAllModelPrices().length;
    }

    public static void printModels(Vehicle vehicle) {
        for (String str : vehicle.getAllModelNames()) {
            System.out.println(str);
        }
    }

    public static void printPrices(Vehicle vehicle) {
        for (double AllPrices : vehicle.getAllModelPrices()) {
            System.out.println(AllPrices);
        }
    }

    public static void outputVehicle(Vehicle v, OutputStream out) throws IOException {
        DataOutputStream dos = new DataOutputStream(out);
        dos.writeUTF(v.getClass().getName());
        dos.writeUTF(v.getMark());
        int len = v.getSizeModelArray();
        dos.writeInt(len);
        try {
            for (int i = 0; i < len; i++) {
                dos.writeUTF(v.getAllModelNames()[i]);
                dos.writeDouble(v.getAllModelPrices()[i]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("File saved, len = " + len);
    }

    public static Vehicle inputVehicle(InputStream in) throws IOException, DuplicateModelNameException {
        DataInputStream dis = new DataInputStream(in);
        Vehicle res = null;
        String cl = dis.readUTF();
        String mark = dis.readUTF();
        int len = dis.readInt();
        if (cl.equals("Auto"))
            res = new Auto(mark, 0);
        else res = new Motorcycle(mark, 0);
        for (int i = 0; i < len; i++) {
            res.addModel(dis.readUTF(), dis.readDouble());
        }
        return res;
    }

    public static void writeVehicle(Vehicle v, Writer out) {
        try {
            PrintWriter printWriter = new PrintWriter(out);
            if (v instanceof Auto) printWriter.println("Auto");
            else printWriter.println("Motorcycle");
            printWriter.println(v.getMark());
            printWriter.println(v.getSizeModelArray());
            for (int i = 0; i < v.getSizeModelArray(); i++) {
                printWriter.println(v.getAllModelNames()[i]);
                printWriter.println(v.getAllModelPrices()[i]);
            }
            printWriter.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Vehicle readVehicle(Reader in) {
        Vehicle vehicle;
        try {
            BufferedReader bufferedReader = new BufferedReader(in);
            String cl = bufferedReader.readLine();
            String mark = bufferedReader.readLine();
            int count = Integer.parseInt(bufferedReader.readLine());
            String[] models = new String[count];
            double[] prices = new double[count];
            if (cl.equals("Auto"))
                vehicle = new Auto(mark, 0);
            else vehicle = new Motorcycle(mark, 0);
            for (int i = 0; i < count; i++) {
                models[i] = bufferedReader.readLine();
                prices[i] = Double.parseDouble(bufferedReader.readLine());
                vehicle.addModel(models[i], prices[i]);
            }
        } catch (IOException | DuplicateModelNameException ex) {
            throw new RuntimeException(ex);
        }
        return vehicle;
    }

    public static double getVehicleAveragePrices(Vehicle...vehicles) {
        double result = 0;                  //...Аргументы переменной (скрывается массив)
        for (Vehicle vehicle : vehicles) {
            result+=averagePrice(vehicle)/vehicles.length;
        }
        return result;
    }

        public static Vehicle createVehicle (Vehicle vehicle, String mark, int size){
        try{
            Class vehicleClass = vehicle.getClass(); //Получили объект класса того же вида что передали
            Vehicle newVehicle = (Vehicle) vehicleClass.getConstructor(String.class, int.class).newInstance(mark, size); //Получили конструктор с указанными типами и кол параметров
            return newVehicle;              //нью инстанс-создаем объект данного типа с перед-ми параметрами
        } catch (Exception e) {
            System.out.println("False"); //Если что то не так то ошибка
        }
        return null;//пустая ссылка(возможно поднять на сторку выше)
    }

    public static void writesVehicle (Vehicle vehicle, Writer out) throws IOException{
        PrintWriter printWriter = new PrintWriter(out);
        printWriter.printf("%s\n",vehicle.getClass().getName()); //%s форматирование данных, показываеся тип переменной и куда будем вставлять
        printWriter.printf("%s\n",vehicle.getMark());
        int lenght = vehicle.getSizeModelArray();
        printWriter.printf("%s\n",lenght);
        try {
            for (int i = 0; i < lenght; i++) {
                printWriter.printf("%s\n",vehicle.getAllModelPrices()[i]);
                printWriter.printf("%s\n",vehicle.getAllModelNames()[i]);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }printWriter.flush();
    }

    public static Vehicle readsVehicle(Reader in) {
        Vehicle vehicle;
        try{
            Scanner scanner = new Scanner(System.in);
            String cl = scanner.next();
            String mark = scanner.next();
            int count = scanner.nextInt();
            String[] models = new String[count];
            double[] prises = new double[count];
            if (cl.equals("Motorcycle"))
                vehicle = new Motorcycle(mark, 0);
            else vehicle = new Auto(mark, 0);
            for (int i = 0; i < count; i++) {
                models[i] = scanner.next();
                prises[i] = scanner.nextDouble();
                vehicle.addModel(models[i], prises[i]);
            }
        } catch (DuplicateModelNameException ex) {
            throw new RuntimeException(ex);
        }
        return vehicle;
    }
}




































//    public static Vehicle readsVehicle(InputStreamReader inputStreamReader) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Input Transport: ");
//        while (scanner.hasNextLine()) {
//            String [] string = scanner.nextLine().split(" "); {
//                for (String str : string) {
//                    System.out.println(str);
//                }
//            }
//        }scanner.close();
//        return null;