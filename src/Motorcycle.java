import MyExeptions.NoSuchModelNameException;
import MyExeptions.DuplicateModelNameException;
import MyExeptions.ModelPriceOutOfBoundsException;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Motorcycle implements Vehicle, Serializable, Cloneable {
    public String getMark() {
        return mark;
    }
    public void setMark(String mark) {
        this.mark = mark;
    }
    private transient long lastModified;
    {
        this.lastModified = System.currentTimeMillis();
    }

    private class Model implements Serializable, Cloneable {
        private String modelName;
        private double price;
        Model prev = null;
        Model next = null;

        public String getModelName() {
            return modelName;
        }

        public double getPrice() {
            return price;
        }

        public Model(String modelName, double price) {
            this.modelName = modelName;
            this.price = price;
        }

        public int hashCode() {
            int result = modelName == null ? 0 : modelName.hashCode();
            result = (int) (result + price);
            return result;
        }

        public String toString() {
            return modelName + "" + price;
        }

        @Override
        public Model clone() throws CloneNotSupportedException {
            return (Model) super.clone();
        }
    }

    private Model head = new Model("Mod", 100);

    {
        size = 1;
        head.prev = head;
        head.next = head;
    }

    private int size;
    private String mark;

    public Motorcycle(String mark, int size) {
        this.mark = mark;
        for (int i = 0; i < size; i++)
            try {
                addModel(mark + i, 300 + i);
            } catch (DuplicateModelNameException pop) {
                System.out.println(pop);
            }
    }

    public void setModelName(String oldName, String newName) throws DuplicateModelNameException, NoSuchModelNameException {
        if (getModelByName(oldName) != null) {
            if (getModelByName(newName) == null) getModelByName(oldName).modelName = newName;
            else throw new DuplicateModelNameException(newName);
        } else {
            throw new NoSuchModelNameException(oldName);
        }
    }

    public String[] getAllModelNames() {
        Model m = head;
        String[] NamesArray = new String[size];
        for (int i = 0; i < size - 1; i++) {
            NamesArray[i] = m.next.getModelName();
            m = m.next;
        }
        return NamesArray;
    }

    public double getPriceModelByName(String modelName) throws NoSuchModelNameException {
        if (getModelByName(modelName) != null) {
            for (int i = 1; i <= size; i++) {
                if (getModelByIndex(i).getModelName().equals(modelName))
                    return getModelByIndex(i).price;
            }
            throw new NoSuchModelNameException(modelName);
        } else {
            throw new NoSuchModelNameException(modelName);
        }
    }

    public void setPriceModelByName(String modelName, double price) throws NoSuchModelNameException {
        if (price > 0) {
            if (getModelByName(modelName) != null) {
                getModelByName(modelName).price = price;
            } else {
                throw new NoSuchModelNameException(modelName);
            }
        } else {
            throw new ModelPriceOutOfBoundsException();
        }
    }

    public double[] getAllModelPrices() {
        double[] pricesArray = new double[size];
        for (int i = 1; i <= size - 1; i++)
            pricesArray[i - 1] = getModelByIndex(i).getPrice();
        return pricesArray;
    }

    public void addModel(String modelName, double price) throws DuplicateModelNameException {
        if (price < 0) throw new ModelPriceOutOfBoundsException();
        if (getModelByName(modelName) == null) {
            Model model = new Model(modelName, price);
            model.next = head;
            model.prev = head.prev;
            model.prev.next = model;
            head.prev = model;
            size++;
        } else {
            throw new DuplicateModelNameException(modelName);
        }
        this.lastModified = System.currentTimeMillis();
    }

    public String getModelName(Model model) {
        return model.getModelName();
    }

    public Model getModelByIndex(int index) {
        Model m;
        m = head;
        int i = 1;
        while (i <= index) {
            m = m.next;
            ++i;
        }
        return m;
    }

    public Model getModelByName(String name) {
        Model model = head.next;
        while (model != head) {
            if (model.getModelName().equals(name)) {
                return model;
            }
            model = model.next;
        }
        return null;
    }

    public void deleteModel(String modelName) throws NoSuchModelNameException {
        if (getModelByName(modelName) != null) {
            Model m;
            m = head;
            for (int i = 1; i <= size; i++) {
                if (getModelByIndex(i).getModelName().equals(modelName)) {
                    m = getModelByIndex(i);
                    break;
                }
            }
            m.prev.next = m.next;
            m.next.prev = m.prev;
            m.next = null;
            m.prev = null;
            size--;
        } else {
            throw new NoSuchModelNameException(modelName);
        }
        this.lastModified = System.currentTimeMillis();
    }

    public int getSizeModelArray() {
        return size;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Mark: ").append(getMark()).append("\n");
        {
            for (int i = 0; i < getAllModelNames().length - 1; i++)
                stringBuffer.append("Model: ").append(getAllModelNames()[i]).append("\n");
            {
                for (int j = 0; j < getAllModelPrices().length - 1; j++)
                    stringBuffer.append("Price: ").append(getAllModelPrices()[j]).append("\n");
            }
            return stringBuffer.toString();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Motorcycle that)) return false;
        if (Objects.equals(this.mark, ((Motorcycle) o).mark)) {
            return Arrays.equals(getAllModelPrices(), ((Motorcycle) o).getAllModelPrices())
                    && Arrays.equals(getAllModelNames(), ((Motorcycle) o).getAllModelNames());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastModified, head, size, mark);
    }

    @Override
    public Motorcycle clone() throws CloneNotSupportedException {
        Motorcycle clones = (Motorcycle) super.clone();
        clones.head = new Model("Opp", 45);
        clones.head.prev = clones.head;
        clones.head.next = clones.head;
        Model last = head;
        clones.size = 0;
        for (int i = 1; i <= size; i++) {
            try {
                clones.addModel(getModelByIndex(i).modelName, getModelByIndex(i).price);
            } catch (DuplicateModelNameException e) {
                e.printStackTrace();
            }
        }
        return clones;
    }
}
