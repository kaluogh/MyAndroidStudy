package com.bignerdranch.android.criminalintent

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class CrimeListFragment : Fragment() {

    interface Callbacks{
        fun onCrimeSelected(crimeId: UUID)
    }

    companion object {
        private const val TAG = "CrimeListFragment"
        fun newInstance() = CrimeListFragment()
    }

    private lateinit var crimeViewModel: CrimeListViewModel
    private lateinit var crimeListView: RecyclerView
    private var crimeAdapter:CrimeAdapter? = CrimeAdapter(emptyList())
    private var callbacks: Callbacks? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_crime_list, menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.crime_list_fragment, container, false)
        crimeListView = view.findViewById(R.id.crime_list_fragment)
        crimeListView.layoutManager = LinearLayoutManager(context)
        crimeListView.adapter = crimeAdapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        crimeViewModel = ViewModelProvider(this).get(CrimeListViewModel::class.java)
        crimeViewModel.crimeListLiveData.observe(
            viewLifecycleOwner,
            { crimes ->
                crimes?.let {
                    Log.i(TAG, "Got crimes ${crimes.size}")
                    updateUI(crimes)
                }
            }
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private fun updateUI(crimes: List<Crime>) {
//        val crimes:List<Crime> = crimeViewModel.crimes
        crimeAdapter = CrimeAdapter(crimes)
        crimeListView.adapter = crimeAdapter
    }


    private inner class CrimeHolder(view: View):RecyclerView.ViewHolder(view), View.OnClickListener {
        private lateinit var crime:Crime
        private var titleTextView:TextView = itemView.findViewById(R.id.crime_title)
        private var dateTextView:TextView = itemView.findViewById(R.id.crime_date)
        private var solveImageView:ImageView = itemView.findViewById(R.id.crime_solved_imageView)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(crime: Crime){
            this.crime = crime
            titleTextView.text = crime.title
            dateTextView.text = crime.date.toString()
            solveImageView.visibility = when{
                crime.isSolved -> View.VISIBLE
                else  -> View.GONE
            }

        }

        override fun onClick(view: View) {
//            Toast.makeText(context, "${crime.title} pressed!", Toast.LENGTH_SHORT).show()
            callbacks?.onCrimeSelected(crime.id)
        }
    }

    private inner class CrimeAdapter(var crimes: List<Crime>):RecyclerView.Adapter<CrimeHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
            val view = layoutInflater.inflate(R.layout.crime_list_item, parent, false)
            return CrimeHolder(view)
        }

        override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
            val crime = crimes[position]
            holder.bind(crime)
        }

        override fun getItemCount(): Int {
            return crimes.size
        }

    }
}