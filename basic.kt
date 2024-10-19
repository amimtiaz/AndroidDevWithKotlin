
// Landmass and Higher Order Functions
fun main() {
    println("The Square of 5: " + sqr(5) )

    println("The Sum is: " + add(5,10))

    printName("Imtiaz")
    printName("New Bangladesh")
}

val sqr = {x: Int -> x*x}

val add: (Int, Int) -> Int = {x,y -> x+y}

val printName = {strName: String -> println("Hello, " + strName) }



// generic class
fun main() {
    val genClass = GenClass<String>("Imtiaz")
    val genClass2 = GenClass<Int>(10)
    val genClass3 = GenClass(true)
    val genClass4 = GenClass(10.5)
}

class GenClass<T>(value: T){
    init {
        println("The value is: " + value)
        Check(value)
    }
}

fun <T> Check(text: T){
    println("Received Value is: " + text)
}




// enum
fun main() {

    println("Today is: " + Days.Monday)

    for (day in Days.values()) {
        if (day.holiday) {
            println("" + day + " is a holiday!")
        }
    }

}

enum class Days(val holiday: Boolean = false){
    Sunday(true),
    Monday,
    Tuesday,
    Wednesday,
    Thursday,
    Friday,
    SATURDAY
}

enum class Direction{
    EAST, WEST, NORTH, SOUTH
}

enum class Gender{
    MALE, FEMALE, TRANS
}

enum class Color(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}



// Interface
interface IClass {
    var operator: String

    // Provide a default implementation for square
    fun square(a: Int): Int {
        return a * a
    }
}

abstract class AbClass {
    var name: String = "John"
    abstract var branch: String

    fun add(a: Int, b: Int): Int {
        return a + b
    }

    abstract fun operate(a: Int, b: Int): Int
}

class MyClass : AbClass() {
    override var branch: String = "Computer"

    override fun operate(a: Int, b: Int): Int {
        return a + b
    }
}

class NewClass : IClass {
    override var operator: String = "Add"

    // Overriding square function to return a cube
    override fun square(a: Int): Int {
        return a * a * a // This now computes the cube instead of square
    }
}


// Classes and Objects
class Car(val brand: String, var year: Int) {
    // Member function
    fun startEngine() {
        println("Engine started!")
    }
}


fun main() {
    val myCar = Car("Toyota", 2021)
    println(myCar.brand) // Accessing property
    myCar.startEngine()   // Calling function
}

// conditional statement ==>  
class MyClass {

    companion object{
        @JvmStatic
        fun main(args:Array<String>){

            var num = 11
            var msg = ""

            msg = if (num>100) "No. is Greater!" else " No. is Smaller!"
            println(msg)

            if (num > 100) {
                println("No. is Greater!")
            } else if (num == 100){
                println("It's equal!")
            }
            else{
                println("No. is Smaller!")
            }

            when(num){
                1->{

                }
                100->{

                }
            }

            when{
                num<100 -> {

                }
                num == 100 -> {

                }
            }
        }
    }
    
}


// For Loop ===>>
            var num = 0
            for (i in 0..10){
                println("Number is : ${num++} ")
            }

            for (i in 0 until 10){
                println("Number is: ${num++}")
            }

            for (i in 10 downTo  0 step 2){
                println("Number is: ${num++}")
            }

            for (i in  0..12 step 3){
                println("Number is: ${num++}")
            }


            val arrNum = ArrayList<Int>()
            arrNum.add(1)
            arrNum.add(2)
            arrNum.add(3)
            arrNum.add(4)
            arrNum.add(5)
            arrNum.add(6)
            arrNum.add(7)

            for (i  in arrNum) {
                println("Array Number is: ${i}")
            }



// Constructors
class Person(val name: String, var age: Int)

class Person {
    var name: String
    var age: Int
    
    // Secondary constructor
    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }
}


// Inheritance
// Base class (must be open to be inherited)
open class Animal(val name: String) {
    open fun makeSound() {
        println("Animal sound")
    }
}

// Derived class
class Dog(name: String): Animal(name) {
    override fun makeSound() {
        println("Bark!")
    }
}

fun main() {
    val myDog = Dog("Buddy")
    myDog.makeSound() // Output: Bark!
}

// Abstract Classes and Methods
abstract class Shape {
    abstract fun area(): Double
}

class Circle(val radius: Double) : Shape() {
    override fun area(): Double {
        return Math.PI * radius * radius
    }
}

fun main() {
    val circle = Circle(5.0)
    println("Area of the circle: ${circle.area()}")
}


// Interfaces
interface Drivable {
    fun drive()
}

class Car : Drivable {
    override fun drive() {
        println("Driving the car")
    }
}

fun main() {
    val myCar = Car()
    myCar.drive()  // Output: Driving the car
}


// Visibility Modifiers
class BankAccount(private var balance: Double) {
    fun deposit(amount: Double) {
        balance += amount
    }

    fun checkBalance() {
        println("Current balance: $balance")
    }
}

fun main() {
    val account = BankAccount(100.0)
    account.deposit(50.0)
    account.checkBalance()  // Output: Current balance: 150.0
    // account.balance is not accessible due to private modifier
}


// Data Classes
data class User(val name: String, val age: Int)

fun main() {
    val user1 = User("Alice", 30)
    val user2 = user1.copy(age = 31)
    
    println(user1)  // Output: User(name=Alice, age=30)
    println(user2)  // Output: User(name=Alice, age=31)
}


// Companion Objects
class MathUtil {
    companion object {
        fun add(a: Int, b: Int): Int {
            return a + b
        }
    }
}

fun main() {
    println(MathUtil.add(5, 10))  // Output: 15
}
