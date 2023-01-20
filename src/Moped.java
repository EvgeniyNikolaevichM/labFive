import java.util.LinkedList;
import java.util.Objects;
import MyExeptions.NoSuchModelNameException;
import MyExeptions.DuplicateModelNameException;
import MyExeptions.ModelPriceOutOfBoundsException;

public class Moped implements Vehicle{
    private String mark;
    public String getMark() {
        return mark;
    }
    public void setMark(String mark) {
        this.mark = mark;
    }
    private final LinkedList<Model> modelLinkedList;//Связанный список, хранит елементы связанные и хранят ссылку на пред и след объект

    private class Model {
        private final String ModelName;
        private double ModelPrice;

        public String getModelName() {
            return ModelName;
        }
        public void setModelPrice(double modelPrice) {
            ModelPrice = modelPrice;
        }
        public double getModelPrice() {
            return ModelPrice;
        }

        public Model(String ModelName, double ModelPrice) {
            this.ModelName = ModelName;
            this.ModelPrice = ModelPrice;
        }
    }

    public Moped(String Mark, int n) {
        this.mark = Mark;
        modelLinkedList = new LinkedList<>();
        for (int i = 0; i < n; i++)
            modelLinkedList.add(new Model(mark + i, 200 + i));
    }

    public void setModelName(String oldName, String newName) throws DuplicateModelNameException, NoSuchModelNameException {
        int index;
        boolean isChange = true;
        double oldPrice = 0;
        for (Model model : modelLinkedList)
            if (Objects.equals(model.getModelName(),newName)) {throw new DuplicateModelNameException(newName);}
        else
            if (Objects.equals(model.getModelName(), oldName)) {
                index = modelLinkedList.indexOf(model);
                oldPrice = model.getModelPrice();
                modelLinkedList.set(index, new Model(newName,oldPrice));
                isChange = false;

               }
        if (isChange) throw new NoSuchModelNameException(oldName);
            }

    public String[] getAllModelNames() {
        int length = modelLinkedList.size();
        String[] NamesArray = new String[length];
        for (int i = 0; i < length; i++) {
            NamesArray[i] = modelLinkedList.get(i).getModelName();
        }
        return NamesArray;
    }

    public double getPriceModelByName(String modelName) throws NoSuchModelNameException {
        for (Model model : modelLinkedList)
            if (model.ModelName.equals(modelName))
                return model.ModelPrice;
        throw new NoSuchModelNameException(modelName);
    }

    public void setPriceModelByName(String modelName, double newPrice) throws NoSuchModelNameException {
        boolean isChange = true;
        if (newPrice < 0) throw new ModelPriceOutOfBoundsException();
        for (Model model : modelLinkedList)
            if (Objects.equals(model.getModelName(), modelName)) {
                model.setModelPrice(newPrice);
                isChange = false;
                break;
            }
        if (isChange) throw new NoSuchModelNameException(modelName);
    }

    public double[] getAllModelPrices() {
        int length = modelLinkedList.size();
        double[] PricesArray = new double[length];
        for (int i = 0; i < length; i++)
            PricesArray[i] = modelLinkedList.get(i).getModelPrice();
        return PricesArray;
    }

    public void addModel(String modelName, double modelPrice) throws DuplicateModelNameException {
        if (modelPrice < 0) throw new ModelPriceOutOfBoundsException();
        for (Model model : modelLinkedList)
            if (Objects.equals(model.getModelName(), modelName)) throw new DuplicateModelNameException(modelName);
        modelLinkedList.add(new Model(modelName, modelPrice));
    }

    public void deleteModel(String Name) throws NoSuchModelNameException {
        boolean flag = true;
        int length = getSizeModelArray();
        for (int i = 1; i < length; i++) {
            if (Objects.equals(modelLinkedList.get(i).getModelName(), Name)) {
                this.modelLinkedList.remove(i);
                flag = false;
            }
            if (flag) throw new NoSuchModelNameException(Name);
        }
    }

    public int getSizeModelArray() {
        return modelLinkedList.size();
    }

    public String toString() {
        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append("Mark: ").append(getMark()).append("\n");
        for (int i = 0; i < getAllModelNames().length; i++) {
            stringBuffer.append("Model: ").append(getAllModelNames()[i]).append("\n");
        }
        for (int i = 0; i < getAllModelPrices().length; i++) {
            stringBuffer.append("Prise: ").append(getAllModelPrices()[i]).append("\n");
        }
        return stringBuffer.toString();
    }
}





































//    public void deleteModel(String Name) throws NoSuchModelNameException {
//        boolean flag = true;
//        for (Model model : modelLinkedList)
//            if (!Objects.equals(model.getModelName(), Name)) {
//                flag=false;
//                modelLinkedList.removeIf(nextModel -> nextModel.getModelName().equals(Name));
//            }
//        if (flag) throw new NoSuchModelNameException(Name);
//    }

//public void deleteModel(String Name) throws NoSuchModelNameException {
//        boolean flag = true;
//        int index = getListIndex(Name);
//            if (!Objects.equals(index, Name)) {
//                flag=false;
//                modelLinkedList.removeIf(nextModel -> nextModel.getModelName().equals(Name));
//            }
//        if (flag) throw new NoSuchModelNameException(Name);
//    }

//    private int getListIndex (String modelName) throws NoSuchModelNameException{
//        int i =0;
//        for (Model model : modelLinkedList){
//            if (model.equals(modelName))
//                return i;
//            i++;
//        }
//        throw new NoSuchModelNameException(modelName);
//    }