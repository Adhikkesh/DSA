class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for(int i=0;i<n-1;i++){
            for(int j=i;j<n;j++){
                swap1(matrix,i,j,n);
            }
        }
        for(int i=0;i<n/2;i++){
            for(int j=0;j<n;j++){
                swap(matrix,i,j,n-i-1);
            }
        }
    }

    public void swap(int[][] matrix,int i,int j,int a){
        int temp = matrix[j][i];
        matrix[j][i] = matrix[j][a];
        matrix[j][a] = temp;
    }

    public void swap1(int[][] matrix,int i,int j,int n){
            int temp = matrix[i][j];
            matrix[i][j] = matrix[j][i];
            matrix[j][i] = temp;
    }

    public void customPrint(int[][] matrix,int n){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
