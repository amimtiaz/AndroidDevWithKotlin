data class Product(
    val id: Int,
    var name: String,
    var price: Double,
    var stock: Int
)

data class Customer(
    val id: Int,
    val name: String,
    var email: String,
    var phone: String
)

data class Order(
    val orderId: Int,
    val customerId: Int,
    val products: List<OrderItem>,
    var totalAmount: Double
)

data class OrderItem(
    val productId: Int,
    var quantity: Int,
    var price: Double
)
class ProductService {
    private val products = mutableListOf<Product>()

    fun addProduct(product: Product): Boolean {
        products.add(product)
        return true
    }

    fun updateProduct(id: Int, newProduct: Product): Boolean {
        val product = products.find { it.id == id } ?: return false
        product.name = newProduct.name
        product.price = newProduct.price
        product.stock = newProduct.stock
        return true
    }

    fun deleteProduct(id: Int): Boolean {
        return products.removeIf { it.id == id }
    }

    fun listProducts(): List<Product> {
        return products
    }

    fun getProductById(id: Int): Product? {
        return products.find { it.id == id }
    }
}
class CustomerService {
    private val customers = mutableListOf<Customer>()

    fun addCustomer(customer: Customer): Boolean {
        customers.add(customer)
        return true
    }

    fun updateCustomer(id: Int, newCustomer: Customer): Boolean {
        val customer = customers.find { it.id == id } ?: return false
        customer.email = newCustomer.email
        customer.phone = newCustomer.phone
        return true
    }

    fun deleteCustomer(id: Int): Boolean {
        return customers.removeIf { it.id == id }
    }

    fun listCustomers(): List<Customer> {
        return customers
    }

    fun getCustomerById(id: Int): Customer? {
        return customers.find { it.id == id }
    }
}

class OrderService(private val productService: ProductService) {
    private val orders = mutableListOf<Order>()

    fun createOrder(order: Order): Boolean {
        if (order.products.isEmpty()) return false

        var totalAmount = 0.0
        order.products.forEach { item ->
            val product = productService.getProductById(item.productId)
            if (product != null && product.stock >= item.quantity) {
                product.stock -= item.quantity
                totalAmount += item.price * item.quantity
            } else {
                println("Insufficient stock for product ${item.productId}")
                return false
            }
        }
        order.totalAmount = totalAmount
        orders.add(order)
        return true
    }

    fun listOrders(): List<Order> {
        return orders
    }
}

object Database {
    val productService = ProductService()
    val customerService = CustomerService()
    val orderService = OrderService(productService)
}


fun mainMenu() {
    println("=== Inventory Management System ===")
    println("1. Manage Products")
    println("2. Manage Customers")
    println("3. Place Orders")
    println("4. Reports")
    println("5. Exit")
}

fun main() {
    var option: Int

    do {
        mainMenu()
        option = readLine()?.toIntOrNull() ?: 5

        when(option) {
            1 -> manageProducts()
            2 -> manageCustomers()
            3 -> placeOrder()
            4 -> generateReports()
            5 -> println("Exiting system...")
            else -> println("Invalid option, please try again.")
        }
    }
fun mainMenu() {
    println("=== Inventory Management System ===")
    println("1. Manage Products")
    println("2. Manage Customers")
    println("3. Place Orders")
    println("4. Reports")
    println("5. Exit")
}

fun main() {
    var option: Int

    do {
        mainMenu()
        option = readLine()?.toIntOrNull() ?: 5

        when(option) {
            1 -> manageProducts()
            2 -> manageCustomers()
            3 -> placeOrder()
            4 -> generateReports()
            5 -> println("Exiting system...")
            else -> println("Invalid option, please try again.")
        }
    } while (option != 5)
}

fun generateReports() {
    println("=== Reports ===")
    println("1. List All Orders")
    println("2. Total Revenue")
    println("3. Back to Main Menu")
    
    when (readLine()?.toIntOrNull()) {
        1 -> {
            println("Listing all orders...")
            val orders = Database.orderService.listOrders()
            orders.forEach {
                println(it)
            }
        }
        2 -> {
            val orders = Database.orderService.listOrders()
            val totalRevenue = orders.sumOf { it.totalAmount }
            println("Total Revenue: $totalRevenue")
        }
        3 -> return
        else -> println("Invalid option")
    }
}

fun generateReports() {
    println("=== Reports ===")
    println("1. List All Orders")
    println("2. Total Revenue")
    println("3. Back to Main Menu")
    
    when (readLine()?.toIntOrNull()) {
        1 -> {
            println("Listing all orders...")
            val orders = Database.orderService.listOrders()
            orders.forEach {
                println(it)
            }
        }
        2 -> {
            val orders = Database.orderService.listOrders()
            val totalRevenue = orders.sumOf { it.totalAmount }
            println("Total Revenue: $totalRevenue")
        }
        3 -> return
        else -> println("Invalid option")
    }
}


fun placeOrder() {
    println("=== Place Order ===")
    println("Enter customer ID:")
    val customerId = readLine()?.toIntOrNull() ?: return

    val orderItems = mutableListOf<OrderItem>()
    do {
        println("Enter product ID and quantity (comma separated), or type 'done' to finish:")
        val input = readLine()
        if (input == "done") break

        val details = input?.split(",") ?: continue
        val productId = details[0].toInt()
        val quantity = details[

