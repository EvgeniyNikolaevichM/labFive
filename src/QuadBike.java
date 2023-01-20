import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import MyExeptions.NoSuchModelNameException;
import MyExeptions.DuplicateModelNameException;
import MyExeptions.ModelPriceOutOfBoundsException;

public class QuadBike implements Vehicle {
    private String mark;
    private ArrayList<Model> modelArrayList; //Массив, который может изменять размер

    public String getMark() {
        return mark;
    }
    public void setMark(String mark) {
        this.mark = mark;
    }

    private class Model {
        private String ModelName;
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

    public QuadBike(String Mark, int n) {
        this.mark = Mark;
        modelArrayList = new ArrayList<>();
        for (int i = 0; i < n; i++)
            modelArrayList.add(new Model(mark + i, 200 + i));
    }

    public void setModelName(String oldName, String newName) throws DuplicateModelNameException, NoSuchModelNameException {
        int index;
        boolean isChange = true;
        double oldPrice = 0;
        for (QuadBike.Model model : modelArrayList)
            if (Objects.equals(model.getModelName(),newName)) {throw new DuplicateModelNameException(newName);}
            else
            if (Objects.equals(model.getModelName(), oldName)) {
                index = modelArrayList.indexOf(model);
                oldPrice = model.getModelPrice();
                modelArrayList.set(index, new Model(newName,oldPrice));
                isChange = false;

            }
        if (isChange) throw new NoSuchModelNameException(oldName);
    }

    public String[] getAllModelNames() {
        int length = modelArrayList.size();
        String[] NamesArray = new String[length];
        for (int i = 0; i < length; i++) {
            NamesArray[i] = modelArrayList.get(i).getModelName();
        }
        return NamesArray;
    }

    public double getPriceModelByName(String modelName) throws NoSuchModelNameException {
        for (Model model : modelArrayList)
            if (model.ModelName.equals(modelName))
                return model.ModelPrice;
        throw new NoSuchModelNameException(modelName);

    }

    public void setPriceModelByName(String modelName, double newPrice) throws NoSuchModelNameException {
        boolean isChange = true;
        if (newPrice < 0) throw new ModelPriceOutOfBoundsException();
        for (Model model : modelArrayList)
            if (Objects.equals(model.getModelName(), modelName)) {
                model.setModelPrice(newPrice);
                isChange = false;
                break;
            }
        if (isChange) throw new NoSuchModelNameException(modelName);
    }

    public double[] getAllModelPrices() {
        int length = modelArrayList.size();
        double[] PricesArray = new double[length];
        for (int i = 0; i < length; i++)
            PricesArray[i] = modelArrayList.get(i).getModelPrice();
        return PricesArray;
    }

    public void addModel(String modelName, double modelPrice) throws DuplicateModelNameException {
        if (modelPrice < 0) throw new ModelPriceOutOfBoundsException();
        for (Model model : modelArrayList)
            if (Objects.equals(model.getModelName(), modelName)) throw new DuplicateModelNameException(modelName);
        modelArrayList.add(new Model(modelName, modelPrice));

    }

    public void deleteModel(String Name) throws NoSuchModelNameException {
        boolean flag = true;
        int length = getSizeModelArray();
        for (int i = 1; i < length; i++){
            if (Objects.equals(modelArrayList.get(i).getModelName(), Name)){
                this.modelArrayList.remove(i);
                flag = false;
            }
        }
        if (flag) throw new NoSuchModelNameException(Name);
    }

    public int getSizeModelArray() {
        return modelArrayList.size();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Mark ").append(getMark()).append("\n");
        for (int i = 0; i < getAllModelNames().length; i++) {
            stringBuffer.append("Model ").append(getAllModelNames()[i]).append("\n");
        }
        for (int i = 0; i < getAllModelPrices().length; i++) {
            stringBuffer.append("Price ").append(getAllModelPrices()[i]).append("\n");
        }
        return stringBuffer.toString();
    }
}