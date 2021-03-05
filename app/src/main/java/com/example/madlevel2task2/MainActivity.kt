package com.example.madlevel2task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.*
import com.example.madlevel2task2.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), StatementAdapter.OnItemClickListener {

    private val statements = arrayListOf<Statement>()
    private val statementAdapter = StatementAdapter(statements, this)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        binding.rvStatements.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvStatements.adapter = statementAdapter
        binding.rvStatements.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        createItemTouchHelper().attachToRecyclerView(binding.rvStatements)

        for (i in Statement.STATEMENTS.indices) {
            statements.add(Statement(Statement.STATEMENTS[i], Statement.ANSWERS[i]))
        }
        statementAdapter.notifyDataSetChanged()
    }

    // If a statement is clicked, a Snackbar message appears with a sneak preview of the right answer.
    override fun onItemClick(position: Int) {
        Snackbar.make(binding.rvStatements, "This statement is " + statements.get(position).answer + ".", Snackbar.LENGTH_LONG).show()
    }

    private fun createItemTouchHelper(): ItemTouchHelper {

        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            // Disables the ability to move items up and down.
            override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // If the user swipes the right way, the statement disappears. If not, a Snackbar message with "Wrong!" appears.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                if ((direction == ItemTouchHelper.LEFT && statements[position].answer == false) || direction == ItemTouchHelper.RIGHT && statements[position].answer == true) {
                    statements.removeAt(position)
                } else {
                    Snackbar.make(binding.rvStatements, R.string.wrong, Snackbar.LENGTH_LONG).show()
                }
                statementAdapter.notifyDataSetChanged()
            }
        }
        return ItemTouchHelper(callback)
    }
}