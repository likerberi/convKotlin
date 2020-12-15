class MainActivity : AppCompatActivity(), View.OnClickListener{
                                            //1-1
    lateinit var button1 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1 = findViewById(R.id.button1)
        button1.setOnClickListener(this)
    }

    //2
    val button2 = findViewById<Button>(R.id.button2)
    button2.setOnClickListener(object : View.OnClickListener {
        override fun onClick(view: View?) {
            //a
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            intent.putExtra("key", "Kotlin")
            startActivity(intent)
            //b
            val intent = Intent(this, DisplayMessageActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, message)
            }
        }
    })

    //1-2
    override fun onClick(view: View?) {
        when(view?.id){
            R.id.button1->{
                // do some work here
            }
        }
    }

    //3
    val listener= View.OnClickListener { view ->
        when (view.getId()) {
            R.id.button2 -> {
                // Do some work here
            }
        }
    }
}