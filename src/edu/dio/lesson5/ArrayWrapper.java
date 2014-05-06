package edu.dio.lesson5;

import java.util.Arrays;

/**
 * Created by Vladimir V. Kravchenko on 04.05.2014.
 */
public class ArrayWrapper {
    private Person[] sourceData;
    private Person[] additionalData;

    private Person[] data;
    private int[] indexes;
    private boolean indexed=false;

    public ArrayWrapper() {
        this(new Person[0], new Person[0]);
    }

    public ArrayWrapper(Person[] sourceData, Person[] additionalData) {
        setSourceData(sourceData);
        setAdditionalData(additionalData);
    }

    public Person[] getSourceData() {
        return sourceData;
    }

    public void setSourceData(Person[] data) {
        indexed=false;
        this.sourceData = makeCopy(data);
    }

    public Person[] getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(Person[] data) {
        indexed=false;
        this.additionalData = makeCopy(data);
    }

    public Person[] getMerge() {
        calculate();
        Person[] result=new Person[getUniqueCount()];
        int currentIndex=0;
        for (int i = 0; i < data.length; i++) {
            if (indexes[i]==TypeOfElement.UNIQUE || indexes[i]==TypeOfElement.DUPLICATED) {
                result[currentIndex]=data[i];
                currentIndex++;
            }
        }
        return result;
    }

    public Person[] getInnerJoin() {
        calculate();
        Person[] result=new Person[getDuplicatedCount()];
        int currentIndex=0;
        for (int i = 0; i < data.length; i++) {
            if (indexes[i]==TypeOfElement.DUPLICATED) {
                result[currentIndex]=data[i];
                currentIndex++;
            }
        }
        return result;
    }

    public Person[] getOuterJoin() {
        calculate();
        Person[] result=new Person[getNotRepeatableCount()];
        int currentIndex=0;
        for (int i = 0; i < data.length; i++) {
            if (indexes[i]==TypeOfElement.UNIQUE) {
                result[currentIndex]=data[i];
                currentIndex++;
            }
        }
        return result;
    }

    private void calculate() {
        if (!indexed) {
            System.out.println("Calculating...");
            data = joinArrays();
            indexDataArray();
            System.out.println("Calculation completed!");
        }
    }

    private int getUniqueCount() {
        int result=0;
        for (int i = 0; i < indexes.length; i++) {
            if (indexes[i]==TypeOfElement.UNIQUE || indexes[i]==TypeOfElement.DUPLICATED) result++;
        }
        return result;
    }

    private int getDuplicatedCount() {
        int result=0;
        for (int i = 0; i < indexes.length; i++) {
            if (indexes[i]==TypeOfElement.DUPLICATED) result++;
        }
        return result;
    }

    private int getNotRepeatableCount() {
        int result=0;
        for (int i = 0; i < indexes.length; i++) {
            if (indexes[i]==TypeOfElement.UNIQUE) result++;
        }
        return result;
    }

    private Person[] joinArrays() { // join with external array
        if (null == sourceData && null == additionalData) return new Person[0];
        if (null == sourceData) return sourceData;

        Person[] temp=new Person[sourceData.length+additionalData.length];
        System.arraycopy(sourceData, 0, temp, 0, sourceData.length);                                    // copy first array
        System.arraycopy(additionalData, 0, temp, sourceData.length, additionalData.length);        // copy second array
        return temp;
    }

    private void indexDataArray() {
        indexes = new int[data.length];
        for (int i = 0; i < data.length-1; i++) {
            for (int j = i+1; j < data.length; j++) {
                if (indexes[j]==TypeOfElement.UNIQUE) {
                    if (data[j].equals(data[i])) {
                        indexes[i]=TypeOfElement.DUPLICATED;
                        indexes[j]=TypeOfElement.DUPLICATE;
                    }
                }
            }
        }
        indexed = true;
    }

    private Person[] makeCopy(Person[] data){
        if (null==data) return new Person[0];

        return  Arrays.copyOf(data, data.length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrayWrapper)) return false;

        ArrayWrapper that = (ArrayWrapper) o;

        if (!Arrays.equals(data, that.data)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return data != null ? Arrays.hashCode(data) : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ArrayWrapper{");
        sb.append("data=").append(Arrays.toString(data));
        sb.append("indexes=").append(Arrays.toString(indexes));
        sb.append('}');
        return sb.toString();
    }
}
