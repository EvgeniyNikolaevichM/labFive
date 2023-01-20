import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import MyExeptions.NoSuchModelNameException;
import MyExeptions.DuplicateModelNameException;
import MyExeptions.ModelPriceOutOfBoundsException;
public class Auto implements Vehicle, Serializable, Cloneable {
    private String mark;
    private Model[] ModelArray;
    public String getMark() {
        return mark;
    }
    public void setMark(String mark) {
        this.mark = mark;
    }

    private class Model implements Serializable, Cloneable {
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

        public int hashCode() {
            int result = ModelName == null ? 0 : ModelName.hashCode();
            result = (int) (result + ModelPrice);
            return result;
        }

        @Override
        public Model clone() throws CloneNotSupportedException {
            Model clone = (Model) super.clone();
            return new Model(ModelName,ModelPrice);
        }
    }

    public Auto(String Mark, int n) {
        mark = Mark;
        ModelArray = new Model[n];
        for (int i = 0; i < n; i++)
            ModelArray[i] = new Model(mark + i, 200 + i);
    }

    @Override
    public Auto clone() throws CloneNotSupportedException {
        Auto clone = (Auto) super.clone();
        clone.ModelArray = this.ModelArray.clone();
        for (int i = 0; i < ModelArray.length; i++) {
            clone.ModelArray[i] = this.ModelArray[i].clone();
        }
        return clone;
    }

    public void setModelName(String oldName, String modelName) throws DuplicateModelNameException, NoSuchModelNameException {
        if (hasModelByName(modelName)) throw new DuplicateModelNameException(modelName);
        int index = getIndexByName(oldName);
        if (index != -1) ModelArray[index].ModelName = modelName;
        else {
            throw new NoSuchModelNameException(oldName);
        }
    }

    public String[] getAllModelNames() {
        int length = ModelArray.length;
        String[] NamesArray = new String[length];
        for (int i = 0; i < length; i++) {
            NamesArray[i] = ModelArray[i].getModelName();
        }
        return NamesArray;
    }

    public double getPriceModelByName(String modelName) throws NoSuchModelNameException {
        for (Model model : ModelArray)
            if (model.ModelName.equals(modelName))
                return model.ModelPrice;
        throw new NoSuchModelNameException(modelName);

    }

    public void setPriceModelByName(String modelName, double newPrice) throws NoSuchModelNameException {
        boolean isChange = true;
        if (newPrice < 0) throw new ModelPriceOutOfBoundsException();
        for (Model model : ModelArray)
            if (Objects.equals(model.getModelName(), modelName)) {
                model.setModelPrice(newPrice);
                isChange = false;
                break;
            }
        if (isChange) throw new NoSuchModelNameException(modelName);
    }

    public double[] getAllModelPrices() {
        int lenght = ModelArray.length;
        double[] PricesArray = new double[lenght];
        for (int i = 0; i < lenght; i++)
            PricesArray[i] = ModelArray[i].getModelPrice();
        return PricesArray;
    }

    public void addModel(String modelName, double modelPrice) throws DuplicateModelNameException {
        if (modelPrice < 0) throw new ModelPriceOutOfBoundsException();
        for (Model model : ModelArray)
            if (Objects.equals(model.getModelName(), modelName)) throw new DuplicateModelNameException(modelName);
        ModelArray = Arrays.copyOf(ModelArray, ModelArray.length + 1);
        ModelArray[ModelArray.length - 1] = new Model(modelName, modelPrice);

    }

    public boolean hasModelByName(String modelName) {
        return Arrays.asList(getAllModelNames()).contains(modelName);
    }

    public int getIndexByName(String modelName) {
        if (ModelArray != null) {
            return Arrays.asList(getAllModelNames()).indexOf(modelName);
        } else {
            return -1;
        }
    }

    public void deleteModel(String Name) throws NoSuchModelNameException {
        boolean flug = true;
        for (int i = 0; i < ModelArray.length; i++)
            if (Objects.equals(ModelArray[i].getModelName(), Name)) {
                flug = false;
                System.arraycopy(ModelArray, i + 1, ModelArray, i, ModelArray.length - i - 1);
                ModelArray = Arrays.copyOf(ModelArray, ModelArray.length - 1);
            }
        if (flug) throw new NoSuchModelNameException(Name);
    }

    public int getSizeModelArray() {
        return ModelArray.length;
    }

    public String toString() {
        /*        StringBuilder sb = new StringBuilder();*/
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Mark: ").append(getMark()).append("\n");
        for (int i = 0; i < getAllModelNames().length; i++) {
            stringBuffer.append("Model: ").append(getAllModelNames()[i]).append("\n");
        }
        for (int i = 0; i < getAllModelPrices().length; i++) {
            stringBuffer.append("Price: ").append(getAllModelPrices()[i]).append("\n");
        }
        return stringBuffer.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Auto)) return false;
        if (Objects.equals(this.mark, ((Auto) o).mark)) {
                return Arrays.equals(getAllModelPrices(),((Auto) o).getAllModelPrices())
                        && Arrays.equals(getAllModelNames(),((Auto) o).getAllModelNames());
            }
        return false;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(mark);
        result = 31 * result + Arrays.hashCode(ModelArray);
        return result;
    }
}