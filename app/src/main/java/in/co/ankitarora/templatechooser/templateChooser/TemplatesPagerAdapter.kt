package `in`.co.ankitarora.templatechooser.templateChooser

import `in`.co.ankitarora.templatechooser.R
import `in`.co.ankitarora.templatechooser.components.CircleButtonView
import `in`.co.ankitarora.templatechooser.kotlin_data.TemplateDetails
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject


class TemplatesPagerAdapter(private val context: Context, private val templateDetails: List<TemplateDetails>) :
    PagerAdapter() {

    private val listOfPagesConfig = mutableListOf<TemplateData>()
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
        Picasso.with(context).load(modelObject.screenshotLinks.iphone)
            .placeholder(android.R.drawable.progress_indeterminate_horizontal)
            .into(layout.findViewById<ImageView>(R.id.image_template))
        collection.addView(layout)
        listOfPagesConfig.add(TemplateData(position, modelObject, modelObject.variations[0].getIconColor()))
        setClickableImageViews(
            layout.findViewById(R.id.variations_icon_holder),
            listOfPagesConfig,
            position,
            0,
            layout.findViewById(R.id.image_template)
        )
        return layout
    }

    private fun setClickableImageViews(
        layout: LinearLayout,
        listOfTemplateData: MutableList<TemplateData>,
        position: Int,
        selectedVariantPosition: Int,
        imageView: ImageView
    ) {
        layout.removeAllViews()
        listOfTemplateData[position].value.variations.mapIndexed { index, data ->
            layout.addView(CircleButtonView(context, null).also {
                val lp = ViewGroup.LayoutParams(100, 100)
                it.layoutParams = lp
                when (selectedVariantPosition) {
                    index -> it.setState(
                        listOfTemplateData[position].value.variations[index].getIconColor(),
                        CircleButtonView.ButtonState.SELECTED
                    )
                    else -> it.setState(
                        listOfTemplateData[position].value.variations[index].getIconColor(),
                        CircleButtonView.ButtonState.NOT_SELECTED
                    )
                }
                it.setOnClickListener {
                    setClickableImageViews(
                        layout,
                        listOfTemplateData,
                        position,
                        index,
                        imageView
                    )
                    listOfPagesConfig[position] = TemplateData(
                        position,
                        listOfPagesConfig[position].value,
                        listOfPagesConfig[position].value.variations[index].getIconColor()
                    )
                    events.onNext(TemplateChooserViewEvents.VariantSelected(listOfPagesConfig[position].value.variations[index].getIconColor()))
                    Picasso.with(context)
                        .load(listOfPagesConfig[position].value.variations[index].screenshotLinks.iphone)
                        .placeholder(android.R.drawable.progress_indeterminate_horizontal)
                        .into(imageView)
                }
            })
        }

        CircleButtonView(context, null)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int = templateDetails.count()

    fun getTemplatesConfigDataInPager(): List<TemplateData> {
        return listOfPagesConfig.toList()
    }
}

data class TemplateData(val id: Int, val value: TemplateDetails, val selectedVariant: String)
