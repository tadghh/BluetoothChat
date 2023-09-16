package com.webianks.bluechat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Created by ramankit on 25/7/17.
 */

class ChatAdapter(val chatData: List<Message>, val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val SENT = 0
    val RECEIVED = 1
    var df: SimpleDateFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder.itemViewType) {

            SENT -> {
                val sentHolder: SentHolder = holder as SentHolder
                sentHolder.sentTV.text = chatData[position].message
                val timeMilliSeconds = chatData[position].time

                sentHolder.timeStamp.text = df.format(Date(timeMilliSeconds))

            }

            RECEIVED -> {
                val recHolder: ReceivedHolder = holder as ReceivedHolder
                recHolder.receivedTV.text = chatData[position].message
                val timeMilliSeconds = chatData[position].time

                recHolder.timeStamp.text = df.format(Date(timeMilliSeconds))
            }

        }
    }

    override fun getItemViewType(position: Int): Int {

        when (chatData[position].type) {
            Constants.MESSAGE_TYPE_SENT -> return SENT
            Constants.MESSAGE_TYPE_RECEIVED -> return RECEIVED
        }

        return -1
    }

    override fun getItemCount(): Int {
        return chatData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when (viewType) {
            SENT -> {
                val view = LayoutInflater.from(context).inflate(R.layout.sent_layout, parent, false)
                return SentHolder(view)
            }

            RECEIVED -> {
                val view =
                    LayoutInflater.from(context).inflate(R.layout.received_layout, parent, false)
                return ReceivedHolder(view)
            }

            else -> {
                val view = LayoutInflater.from(context).inflate(R.layout.sent_layout, parent, false)
                return SentHolder(view)
            }
        }
    }

    inner class SentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var sentTV: TextView = itemView.findViewById<TextView>(R.id.sentMessage)
        var timeStamp: TextView = itemView.findViewById<TextView>(R.id.timeStamp)
    }

    inner class ReceivedHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var receivedTV: TextView = itemView.findViewById<TextView>(R.id.receivedMessage)
        var timeStamp: TextView = itemView.findViewById<TextView>(R.id.timeStamp)
    }

}