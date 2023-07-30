package dev.lynko.cources2023

//interface Chat {
//    fun getChannels(): List<Channel>
//    fun getMessages(channelId: Long): List<Message>
//}
//
//class FakeChatFactory: ChatFactory() {
//    val fakeChat: Chat? = null
//    override fun getChat() = fakeChat ?: FakeChat()
//}
//class SDKChatFactory: ChatFactory() {
//    override fun getChat() = SDKChat()
//}
//
//class LocalChatFactory: ChatFactory() {
//    override fun getChat() = LocalChat()
//}
//
//class FakeChat: Chat {
//    override fun getChannels(): List<Channel> {
//        return listOf(Channel(id = 1,
//            name = "First Channel",
//            users = listOf(User.Self, User.Other("Tom"))))
//    }
//    override fun getMessages(channelId: Long): List<Message>{
//        return listOf(Message(id = 1, text = "Hello"),
//            Message(id = 2, text = "How are you?"))
//
//    }
//}
//
//class SDKChat: Chat {
//    override fun getChannels(): List<Channel> {
//        return listOf(Channel(id = 1,
//            name = "First Channel",
//            users = listOf(User.Self, User.Other("Tom"))))
//    }
//    override fun getMessages(channelId: Long): List<Message>{
//        return listOf(Message(id = 1, text = "Hello"),
//            Message(id = 2, text = "How are you?"))
//
//    }
//}
//
//class LocalChat: Chat {
//    override fun getChannels(): List<Channel> {
//        return listOf()
//    }
//    override fun getMessages(channelId: Long): List<Message>{
//        return listOf()
//
//    }
//}
//
//abstract class ChatFactory {
//    abstract fun getChat(): Chat
//
//    companion object {
//        inline fun <reified T : Chat> create(): ChatFactory =
//            when (T::class) {
//                FakeChat::class -> FakeChatFactory()
//                SDKChat::class -> SDKChatFactory()
//                LocalChat::class -> LocalChatFactory()
//                else -> throw IllegalArgumentException()
//            }
//    }
//}
//
//data class Channel(
//    val id: Long,
//    val name: String,
//    val users: List<User>
//)
//
//data class Message(
//    val id: Long,
//    val text: String
//)
//
//sealed class User {
//    object Self : User()
//    class Other(val name: String) : User()
//}
//
//fun main() {
//    val chat = ChatFactory.create<FakeChat>().getChat()
//    val sdkChat = ChatFactory.create<SDKChat>().getChat()
//    val localChat = ChatFactory.create<LocalChat>().getChat()
//    chat.getChannels()
//}



//_______________

//interface ChristmasTree {
//    fun decorate(): String
//}
//
//class PineChristmasTree : ChristmasTree {
//
//    override fun decorate() = "Christmas tree"
//}
//
//abstract class TreeDecorator (private val tree: ChristmasTree) : ChristmasTree {
//
//    override fun decorate(): String {
//        return tree.decorate()
//    }
//}
//
//class BubbleLights(tree: ChristmasTree) : TreeDecorator(tree) {
//
//    override fun decorate(): String {
//        return super.decorate() + decorateWithBubbleLights()
//    }
//
//    private fun decorateWithBubbleLights(): String {
//        return " with Bubble Lights"
//    }
//}
//
//fun main() {
//    christmasTreeWithBubbleLights()
//}
//
//fun christmasTreeWithBubbleLights() {
//
//    val christmasTree = BubbleLights(PineChristmasTree())
//    val decoratedChristmasTree = christmasTree.decorate()
//    println(decoratedChristmasTree)
//}

//______

//
//data class DisplayDataType(val index: Float, val data: String)
//
//data class DatabaseData(val position: Int, val amount: Int)
//
//class DatabaseDataGenerator {
//    fun generateData(): List<DatabaseData> {
//        val list = arrayListOf<DatabaseData>()
//        list.add(DatabaseData(2, 2))
//        list.add(DatabaseData(3, 7))
//        list.add(DatabaseData(4, 23))
//        return list
//    }
//}
//
//class DataDisplay {
//    fun displayData(data: DisplayDataType) {
//        println("Data is displayed: ${data.index} - ${data.data}")
//    }
//}
//
//interface DatabaseDataConverter {
//    fun convertData(data: List<DatabaseData>): List<DisplayDataType>
//}
//class DataDisplayAdapter(val display: DataDisplay): DatabaseDataConverter {
//    override fun convertData(data: List<DatabaseData>): List<DisplayDataType> {
//        val returnList = arrayListOf<DisplayDataType>()
//        for (datum in data) {
//            val ddt = DisplayDataType(datum.position.toFloat(), datum.amount.toString())
////            display.displayData(ddt)
//            returnList.add(ddt)
//        }
//        return returnList
//    }
//}
//
//fun main() {
//    val generator = DatabaseDataGenerator()
//    val generatedData = generator.generateData()
//    val adapter = DataDisplayAdapter(DataDisplay())
//    val convertData = adapter.convertData(generatedData)
//    convertData.forEach {
//        adapter.display.displayData(it)
//    }
//}


//_______________


//enum class Language {
//    English,
//    Italian,
//    French;
//}
//
//interface Translator {
//    fun translate(text: String, textLanguage: Language)
//}
//
//class ItalianTranslator : Translator {
//    override fun translate(text: String, textLanguage: Language) {
//        println("Translate ($text) from ${textLanguage.name} to Italian")
//    }
//}
//
//class FrenchTranslator : Translator {
//    override fun translate(text: String, textLanguage: Language) {
//        println("Translate ($text) from ${textLanguage.name} to French")
//    }
//}
//
//class EnglishTranslator : Translator {
//    override fun translate(text: String, textLanguage: Language) {
//        println("Translate ($text) from ${textLanguage.name} to English")
//    }
//}
//
//class TranslationManager {
//    private val italianTranslator = ItalianTranslator()
//    private val frenchTranslator = FrenchTranslator()
//    private val englishTranslator = EnglishTranslator()
//
//    fun translate(text: String, translateFrom: Language, translateTo: Language) {
//        when (translateTo) {
//            Language.Italian -> italianTranslator.translate(text, translateFrom)
//            Language.French -> frenchTranslator.translate(text, translateFrom)
//            Language.English -> englishTranslator.translate(text, translateFrom)
//        }
//    }
//}
//
//fun main() {
//    val translationManager = TranslationManager()
//    translationManager.translate("Some text", Language.English, Language.Italian)
//    translationManager.translate("Some text", Language.English, Language.French)
//}

//_________

//interface Observer {
//    fun onEvent(text: String)
//}
//
//class EventObserver1(): Observer {
//    override fun onEvent(string: String) {
//        println("EventObserver1 receive data ")
//    }
//}
//
//
//class EventObserver2(): Observer {
//    override fun onEvent(string: String) {
//        println("EventObserver2 receive data ")
//    }
//}
//
//class EventSender() {
//    val observersList = mutableListOf<Observer>()
//
//    fun registerObsever(observer: Observer) {
//        observersList.add(observer)
//    }
//
//    fun unregisterObsever(observer: Observer) {
//        observersList.remove(observer)
//    }
//
//    fun sendEvent() {
//        observersList.forEach {
//            it.onEvent("Hello")
//        }
//    }
//}

//___________


//class Context(private val strategy: Strategy) {
//    fun executeStratage() = strategy.use()
//}
//
//interface Strategy {
//    fun use()
//}
//
//class Strategy1: Strategy {
//    override fun use() {
//        println("User strategy 1")
//    }
//}
//
//class Strategy2: Strategy {
//    override fun use() {
//        println("User strategy 2")
//    }
//}
//
//fun main() {
//    val context = Context(Strategy2())
//    context.executeStratage()
//}
//


//_________

//interface Command {
//
//    fun execute()
//}
//
//class OnCommand(private val ce: ConsumerElectronics) : Command {
//
//    override fun execute() {
//        ce.on()
//    }
//}
//
//class MuteAllCommand(private val ce: ConsumerElectronics) : Command {
//
//    override fun execute() {
//        ce.mute()
//    }
//}
//
//interface ConsumerElectronics {
//    fun on()
//    fun mute()
//}
//
//class Television : ConsumerElectronics {
//
//    override fun on() {
//        println("Television is on!")
//    }
//
//    override fun mute() {
//        println("Television is muted!")
//    }
//}
//
//class SoundSystem : ConsumerElectronics {
//
//    override fun on() {
//        println("Sound system is on!")
//    }
//    override fun mute() {
//        println("Sound system is muted!")
//    }
//}
//
//class Button(var c: Command) {
//
//    fun click() {
//        c.execute()
//    }
//}
//
//class UniversalRemote {
//
//    // here we will have a complex electronic circuit :-)
//    // that will maintain current device
//    fun getActiveDevice() : ConsumerElectronics{
//        val tv = Television()
//        return tv
//    }
//}
//
//fun main() {
//
//    val ce = UniversalRemote().getActiveDevice()
//    val onCommand = OnCommand(ce)
//    val onButton = Button(onCommand)
//    onButton.click()
//
//    val tv = Television()
//    val ss = SoundSystem()
//
//    val muteTv = MuteAllCommand(tv)
//    val muteSound = MuteAllCommand(ss)
//    val muteTvButton = Button(MuteAllCommand(tv))
//    muteTvButton.click()
//    val muteSsButton = Button(MuteAllCommand(ss))
//    muteSsButton.click()
//}

//_________

//class AlertStateContext() {
//
//    private var currentState: MobileState = MobileState.StateDefault
//
//    fun setState(newState: MobileState) {
//        currentState = newState
//    }
//
//    fun alert() {
//        currentState.alert(this)
//    }
//
//    sealed class MobileState {
//        object StateVibration: MobileState() {
//            override fun alert(context: AlertStateContext) {
//                print("vibration")
//            }
//
//        }
//
//        object StateSound: MobileState() {
//            override fun alert(context: AlertStateContext) {
//                print("play sound")
//            }
//
//        }
//
//        object StateDefault: MobileState() {
//            override fun alert(context: AlertStateContext) {
//                print("default state: no vibro, no sound")
//            }
//        }
//        abstract fun alert(context: AlertStateContext)
//    }
//}
//
//fun main() {
//     val stateContext = AlertStateContext()
//    stateContext.alert()
//    stateContext.setState(AlertStateContext.MobileState.StateVibration)
//    stateContext.alert()
//}