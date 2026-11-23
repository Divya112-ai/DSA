import java.util.ArrayList;
import java.util.List;

class ProductOfNumbers {
    private List<Integer> prefixProducts;

    public ProductOfNumbers() {
        prefixProducts = new ArrayList<>();
        // Initialize with 1 as a neutral base for multiplication
        prefixProducts.add(1);
    }
    
    public void add(int num) {
        if (num == 0) {
            // Reset the list because any product including this 0 will be 0
            prefixProducts = new ArrayList<>();
            prefixProducts.add(1);
        } else {
            // Multiply the new number by the last prefix product
            int lastProduct = prefixProducts.get(prefixProducts.size() - 1);
            prefixProducts.add(lastProduct * num);
        }
    }
    
    public int getProduct(int k) {
        int n = prefixProducts.size();
        
        // Note: n includes the initial dummy '1', so there are (n - 1) valid numbers.
        // If k >= n, it means a 0 was encountered within the last k numbers.
        if (k >= n) {
            return 0;
        }
        
        int totalProduct = prefixProducts.get(n - 1);
        int productBeforeK = prefixProducts.get(n - 1 - k);
        
        return totalProduct / productBeforeK;
    }
}
