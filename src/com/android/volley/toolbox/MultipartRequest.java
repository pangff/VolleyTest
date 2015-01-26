package com.android.volley.toolbox;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import ch.boye.httpclientandroidlib.HttpEntity;
import ch.boye.httpclientandroidlib.entity.mime.HttpMultipartMode;
import ch.boye.httpclientandroidlib.entity.mime.MultipartEntityBuilder;
import ch.boye.httpclientandroidlib.entity.mime.content.FileBody;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;

public class MultipartRequest extends Request<JSONObject> {

	// private MultipartEntity entity = new MultipartEntity();

	MultipartEntityBuilder entity = MultipartEntityBuilder.create();
	HttpEntity httpentity;
	private String FILE_PART_NAME = "file";

	private final Response.Listener<JSONObject> mListener;
	private final File mFilePart;
	private final Map<String, String> mStringPart;

	public MultipartRequest(String url, Response.ErrorListener errorListener,
			Response.Listener<JSONObject> listener, File file,String uploadName,
			Map<String, String> mStringPart) {
		super(Method.POST, url, errorListener);
		FILE_PART_NAME = uploadName;
		mListener = listener;
		mFilePart = file;
		this.mStringPart = mStringPart;
		entity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		buildMultipartEntity();
	}

	public void addStringBody(String param, String value) {
		mStringPart.put(param, value);
	}

	private void buildMultipartEntity() {
		entity.addPart(FILE_PART_NAME, new FileBody(mFilePart));
		for (Map.Entry<String, String> entry : mStringPart.entrySet()) {
			entity.addTextBody(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public String getBodyContentType() {
		return httpentity.getContentType().getValue();
	}

	@Override
	public byte[] getBody() throws AuthFailureError {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			httpentity = entity.build();
			httpentity.writeTo(bos);
		} catch (IOException e) {
			VolleyLog.e("IOException writing to ByteArrayOutputStream");
		}
		return bos.toByteArray();
	}

	@Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString =
                new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

	@Override
	protected void deliverResponse(JSONObject response) {
		mListener.onResponse(response);
	}
}