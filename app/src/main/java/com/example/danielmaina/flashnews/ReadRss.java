package com.example.danielmaina.flashnews;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by danielmaina on 11/1/16.
 */
public class ReadRss extends AsyncTask<Void, Void, Void> {

    Context context;
    String address = "http://www.thestar.com.my/rss/editors-choice/news/";
    ProgressDialog progressDialog;
    URL url;
    //recyclerView Object for this class
    RecyclerView recyclerView;
    //defining the ArrayList
    ArrayList<FeedItem>feedItems;


    //constructor for the class
    public ReadRss(Context context, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView=recyclerView;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading News...");
    }



    @Override
    protected void onPreExecute() {
        progressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
    }

    @Override
    protected Void doInBackground(Void... params) {
        ProcessXml(Getdata());

        return null;
    }

    private void ProcessXml(Document data) {
        if (data != null) {
            //utilising the node interphase
            //object to store our root element

            //creating an arrayList to store a single news Item
            feedItems = new ArrayList<>();

            Element root = data.getDocumentElement();
            //node to store channel info in the xml document
            Node channel = root.getChildNodes().item(1);
            //the NodeList should store the child items inside the channel
            NodeList items = channel.getChildNodes();
            //looping through each child of the NodeList items
            for (int i = 0; i < items.getLength(); i++) {
                Node cureentchild = items.item(i);

                if (cureentchild.getNodeName().equalsIgnoreCase("item")) {
                    FeedItem item=new FeedItem();
                    NodeList itemchilds = cureentchild.getChildNodes();

                    //looping through the childrenTags in the current items
                    for (int j = 0; j < itemchilds.getLength(); j++) {
                        Node cureent = itemchilds.item(j);
                        //the equalsIgnoreCase method checks to see if the current node is similar to the string in the brackets
                        if (cureent.getNodeName().equalsIgnoreCase("title")){
                            item.setTitle(cureent.getTextContent());
                            item.getTitle();
                        }else if (cureent.getNodeName().equalsIgnoreCase("description")){
                            item.setDescription(cureent.getTextContent());
                            item.getDescription();
                        }else if (cureent.getNodeName().equalsIgnoreCase("pubDate")){
                            item.setPubDate(cureent.getTextContent());
                            item.getPubDate();
                        }else if (cureent.getNodeName().equalsIgnoreCase("link")){
                            item.setLink(cureent.getTextContent());
                            item.getLink();
                        }else if (cureent.getNodeName().equalsIgnoreCase("media:content")){
                            //this should return the Url of the thumbnail store in the media:content tag
                            String url = cureent.getAttributes().item(0).getTextContent();
                            item.setThumbnailUrl(url);
                        }
                    }

                    //storing the news Item in the arrayList after every Loop
                    feedItems.add(item);

                }
            }
        }
    }




    public Document Getdata() {
        try {
            url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDoc = builder.parse(inputStream);
            return xmlDoc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
