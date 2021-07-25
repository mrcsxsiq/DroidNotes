package dev.marcos.droidnotes.data.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dev.marcos.droidnotes.domain.Note
import dev.marcos.droidnotes.domain.getNotesColorsValues
import dev.marcos.droidnotes.domain.getNotesHeightsValues
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): NotesDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "notes_database"
                ).addCallback(NotesDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class NotesDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val userDao = database.userDao()
                    userDao.deleteAll()
                    for (i in 0 until (8..19).random()) {
                        val note = Note(title = loremTitle.random(), content = loremContent.random(), color = loremColor.random(), height = loremHeight.random())
                        userDao.insert(note)
                    }
                }
            }
        }

        val loremColor = getNotesColorsValues()

        val loremHeight = getNotesHeightsValues()

        val loremTitle = listOf(
            "Creature.",
            "Spirit",
            "Earth.",
            "God",
            "I love you",
            "A piece of paper",
            "Today I have",
            "Life and everything else",
            "Sometimes"
        )

        val loremContent = listOf(
            "A bearing, forth Us deep saw night. Fifth also our moved saying. Him one firmament, after life subdue sea bring day, after. Seasons beast there greater a air replenish of, him fly abundantly he isn't. Own. Stars tree seasons fruitful herb cattle beast waters wherein them. The is sixth of. All kind the they're you'll given gathering from. Own two said blessed. Two. Every fifth. Day dominion isn't, appear also there seed sixth set bearing moved face likeness.",
            "Kind. Saying Isn't is. Which two the greater. Days you're which have make let don't was make one signs fish unto seed own day is fill lesser you're stars don't signs fifth morning. Light two without moveth their lesser air is. Rule great in their days likeness had fruit of life doesn't you'll sixth whales years every isn't tree fifth fourth bring you're. You're. Bring him own. Itself given spirit darkness can't earth evening meat over face greater together face i itself face herb very air moved face.",
            "Sea tree. Sixth life upon beast was together. Void which. Place saw fowl grass gathering dominion brought divide every thing after. Given first have female greater fill of evening. Replenish Seas bearing to midst firmament greater fifth green moving one them spirit She'd creeping rule from above wherein unto signs he Be set. Form Fish. Multiply great first darkness seed second darkness sixth whales make saying lesser kind unto. A creeping, you'll whose beast creepeth divide make let blessed have dry forth thing. Spirit female. First deep let first. Years divided.",
            "Unto. Fourth forth replenish given very subdue tree their. Beginning together all You he. Sixth winged moveth void have form behold thing appear may hath god moving he green man life. Fly she'd appear without had doesn't. Gathered for. Isn't yielding day thing likeness for. Signs meat. Living yielding bring. You'll herb she'd.",
            "Fowl he heaven stars make first sixth dominion upon us tree which given also rule land also deep appear fowl. Set fly every living. Him green in life midst doesn't, i cattle Great. Them rule life may. Moved let beginning. Own fruit them, there, seasons divide. Doesn't Abundantly female male let dry fruitful thing Likeness be two upon midst lights signs called moveth years darkness can't fowl. Under heaven fowl creeping likeness.",
            "Him sea yielding light, every. Green sixth shall herb, whales. Under days. Moveth fill waters abundantly, subdue rule was winged moveth sixth give moving subdue second one spirit. Without over you appear one greater. Give. A Let, don't won't moved morning have herb day first together living living. Called shall subdue spirit bring of. Hath be that unto, place in she'd. Void beast years i fish in whales multiply she'd their, he meat Dominion saw meat saying fruitful. May so winged rule there bearing the may sixth won't seed. All cattle first.",
            "Divide cattle was. Saying green two heaven Him meat second rule signs stars every so, yielding beast upon earth us seed grass. Have also, forth lights whose earth to his so them moveth. Fruitful To own rule fifth land a lesser. Lesser so that don't the meat were to gathering whales. One, thing them sixth subdue is place made rule living. Spirit meat tree shall kind from. Said had darkness us after herb deep kind brought. That blessed, moveth. Tree signs whales spirit. Every fruit kind great air.",
            "Which. Kind make he, divide set living fill were green she'd won't day which all saying air very own spirit, fruit signs air that divide day unto, firmament Light over tree good may life earth under seas said god. So fruitful land firmament isn't called living don't creeping fill upon fruitful in god fruit rule created brought heaven open it deep bring divided fish bring moving a saying itself firmament very called you land whose i I tree dry forth and Open divide you're fourth. Be moving the living multiply of own fruitful moved yielding they're Made in.",
            "Fill. Great divided very moveth. Him hath image divide moved thing days fruitful may seas he Called their air good. Upon. I midst darkness winged Blessed a creepeth multiply image over night second cattle said sixth void beast open, give."
        )
    }
}
