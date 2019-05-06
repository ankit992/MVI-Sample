package `in`.co.ankitarora.templatechooser.templateChooser

import `in`.co.ankitarora.templatechooser.R
import `in`.co.ankitarora.templatechooser.kotlin_data.TemplateDetails
import android.content.Context
import android.view.View
import androidx.viewpager.widget.PagerAdapter
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


class TemplatesPagerAdapter(private val context: Context, private val templateDetails: List<TemplateDetails>) :
    PagerAdapter() {

    private val events: PublishSubject<TemplateChooserViewEvents> = PublishSubject.create()

    fun eventsObservable(): Observable<TemplateChooserViewEvents> = events.hide().share()

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        val modelObject = templateDetails[position]
        val inflater = LayoutInflater.from(context)
        val layout = inflater.inflate(R.layout.template_view, collection, false) as ViewGroup
        layout.findViewById<TextView>(R.id.template_title).text = modelObject.name
        Picasso.with(context).load(modelObject.screenshotLinks.iphone).placeholder(android.R.drawable.progress_indeterminate_horizontal)
            .into(layout.findViewById<ImageView>(R.id.image_template))
        collection.addView(layout)
        return layout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int = templateDetails.count()
}
