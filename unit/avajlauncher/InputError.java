package unit.avajlauncher;

class InputError extends Exception {
   
   private int line;
   private String errorMsg = "Open or permission error";

   public InputError(int line, String errorMsg)
   {
      this.line = line;
      this.errorMsg = errorMsg;
   }

   public String toString() {
      return "Input error at line " + this.line + ". Problem definition: " + errorMsg;
   }
}