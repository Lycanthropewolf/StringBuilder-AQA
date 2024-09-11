public class Main {
    public static void main(String[] args) {
        MyStringBuilder myStringBuilder = new MyStringBuilder();

        myStringBuilder.append("Здравствуй");
        System.out.println(myStringBuilder.toString());
        myStringBuilder.append(", Мир!");
        System.out.println(myStringBuilder.toString()); // Hello, World!

        myStringBuilder.delete(5, 7); // удаляем ", "
        System.out.println(myStringBuilder.toString()); // HelloWorld!

        myStringBuilder.undo(); // восстанавливаем последнее состояние
        System.out.println(myStringBuilder.toString()); // Hello, World!

        myStringBuilder.undo(); // восстанавливаем еще раз
        System.out.println(myStringBuilder.toString()); // Hello
    }
}