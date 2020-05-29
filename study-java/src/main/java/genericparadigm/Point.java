package genericparadigm;
// 定义泛型类

class Point<T1 extends Number, T2 extends Number,R extends Number>{
    T1 x;
    T2 y;
    public T1 getX() {
        return x;
    }
    public void setX(T1 x) {
        this.x = x;
    }
    public T2 getY() {
        return y;
    }
    public void setY(T2 y) {
        this.y = y;
    }
    
    public R add(T1 x,T2 y,R r) {
    	long a = x.longValue();
    	return r;
    }
}