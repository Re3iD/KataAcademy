public enum Operation {
    SUM {
        public int action(String x, String y) {
            int op = Integer.parseInt(x)+Integer.parseInt(y);
            return op;
        }},
    MULT{
        public int action(String x, String y) {
            int op = Integer.parseInt(x)*Integer.parseInt(y);
            return op;
        }},
    DIVIDE{
        public int action(String x, String y) {
            int op = Integer.parseInt(x)/Integer.parseInt(y);
            return op;
        }},
    MINUS{
        public int action(String x, String y) {
            int op = Integer.parseInt(x)-Integer.parseInt(y);
            return op;
        }};

    public abstract int action(String x, String y);
}
