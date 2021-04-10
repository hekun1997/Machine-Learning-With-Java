package kNN;

import java.util.*;

public class kNN {

    /**
     * kNN model, has three param, x, y, label.
     */
    public static class kNNModel implements Comparable<kNNModel>{
        //coordinate: x, y
        double x;
        double y;
        double distance;
        String type;

        public kNNModel(double x, double y, String type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }

        @Override
        public int compareTo(kNNModel anotherModel) {
            return Double.valueOf(this.distance).compareTo(anotherModel.distance);
        }
    }

    /**
     * calculation the kNN model with test data set.
     * @return
     */
    public static void calcDistance(List<kNNModel> kNNModels, kNNModel inputData){
        double distance;
        for (kNNModel kNNModel: kNNModels) {
            distance = Math.sqrt((kNNModel.x - inputData.x) + (kNNModel.y) - inputData.y);
            kNNModel.distance = distance;
        }
    }

    /**
     * get classification.
     * @param kNNModels
     * @param k
     * @return
     */
    private static String getPredictiveClassification(List<kNNModel> kNNModels, int k){
        String result = "";
        Map<String, Integer> countMap = new HashMap<>();
        Collections.sort(kNNModels);//sort the data
        for(int i = 0; i < k; i++){
            kNNModel kNNModel = kNNModels.get(i);
            Integer count = countMap.getOrDefault(kNNModel.type, 0);
            countMap.put(kNNModel.type, count+1);
        }

        Integer tempVal = 0;
        for(Map.Entry<String, Integer> entry : countMap.entrySet()){
            if(entry.getValue() > tempVal){
                tempVal = entry.getValue();
                result = entry.getKey();
            }
        }

        return result;
    }

    public static void main(String[] args) {
        //data
        List<kNNModel> knnModelList = new ArrayList<kNNModel>();
        knnModelList.add(new kNNModel(1.1, 1.1, "A"));
        knnModelList.add(new kNNModel(1.2, 1.1, "A"));
        knnModelList.add(new kNNModel(1.1, 1.0, "A"));
        knnModelList.add(new kNNModel(3.0, 3.1, "B"));
        knnModelList.add(new kNNModel(3.1, 3.0, "B"));
        knnModelList.add(new kNNModel(5.4, 6.0, "C"));
        knnModelList.add(new kNNModel(5.5, 6.3, "C"));
        knnModelList.add(new kNNModel(6.0, 6.0, "C"));
        knnModelList.add(new kNNModel(10.0, 12.0, "M"));

        kNNModel inputData = new kNNModel(5.1, 6.2, "NB");

        calcDistance(knnModelList, inputData);
        String result = getPredictiveClassification(knnModelList, 3);
        
        System.out.println(result);
    }
}
