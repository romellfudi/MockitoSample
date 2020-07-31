package fudi.freddy.mokitosample.share

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import fudi.freddy.mokitosample.R
import fudi.freddy.mokitosample.share.ShareContract.ShareView

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 7/25/20
 */
class ShareFragment @SuppressLint("ValidFragment") internal constructor() : Fragment(), ShareView {

    var editText: EditText? = null
    var save: Button? = null
    var load: Button? = null
    var listView: ListView? = null
    var stringArrayAdapter: ArrayAdapter<String>? = null
    var sharePresent: SharePresent = SharePresent(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_share, container, false)
        editText = view.findViewById<View>(R.id.et) as EditText
        save = view.findViewById<View>(R.id.save) as Button
        load = view.findViewById<View>(R.id.load) as Button
        listView = view.findViewById<View>(R.id.list) as ListView
        stringArrayAdapter = ArrayAdapter(activity, android.R.layout.simple_list_item_1)
        listView!!.adapter = stringArrayAdapter
        save!!.setOnClickListener {
            stringArrayAdapter!!.add(editText!!.text.toString())
            sharePresent.saveInput(editText!!.text.toString())
        }
        load!!.setOnClickListener { sharePresent.loadInput() }
        listView!!.onItemClickListener = OnItemClickListener { adapterView, _, i, _ ->
            sharePresent.display(adapterView.getItemAtPosition(i) as String)
        }
        return view
    }

    override fun clearText() {
        editText!!.setText("")
    }

    override fun loadText(text: String?) {
        editText!!.setText(text)
    }

    override fun reLoadList() {
        stringArrayAdapter!!.notifyDataSetChanged()
    }

    companion object {
        val instance: ShareFragment
            get() = ShareFragment()
    }
}