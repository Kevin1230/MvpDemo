package com.example.along.mvpdemo.rx;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MultipartBuilder {

  /**
   * 用于把多个File对象转化成MultipartBody
   *
   * @param files 文件集合
   * @param fileKey 和后台约定的文件key
   * @return MultipartBody 对象
   * @author long
   * create at 2017/8/16 上午8:07
   */
  public static MultipartBody filesToMultipartBody(List<File> files, String fileKey) {

    MultipartBody.Builder builder = new MultipartBody.Builder();

    for (File file : files) {
      RequestBody requestBody = RequestBody.create(MediaType.parse(guessMimeType(file.getName())), file);
      builder.addFormDataPart(fileKey, file.getName(), requestBody);
    }

    builder.setType(MultipartBody.FORM);
    MultipartBody multipartBody = builder.build();
    return multipartBody;
  }

  /**
   * 用于把多个File对象转化成MultipartBody
   *
   * @param files 文件集合
   * @param fileKeys 和后台约定的文件key集合
   * @return MultipartBody 对象
   * @author long
   * create at 2017/8/16 上午8:07
   */
  public static MultipartBody filesToMultipartBody(List<File> files, List<String> fileKeys, Map<String, String> params) {

    MultipartBody.Builder builder = new MultipartBody.Builder();

    //添加参数
    addParams(builder, params);

    for (int i = 0; i < files.size(); i++) {
      RequestBody requestBody = RequestBody.create(MediaType.parse(guessMimeType(files.get(i).getName())), files.get(i));
      builder.addFormDataPart(fileKeys.get(i), files.get(i).getName(), requestBody);
    }

    builder.setType(MultipartBody.FORM);
    MultipartBody multipartBody = builder.build();
    return multipartBody;
  }


  /**
   * 用于把多个File对象转化成MultipartBody(带参数)
   *
   * @param files 文件集合
   * @param fileKey 和后台约定的文件key
   * @param params 参数
   * @return MultipartBody 对象
   * @author long
   * create at 2017/8/16 上午8:07
   */
  public static MultipartBody filesToMultipartBody(List<File> files, String fileKey, Map<String, String> params) {

    MultipartBody.Builder builder = new MultipartBody.Builder();

    //添加参数
    addParams(builder, params);

    for (File file : files) {
      RequestBody requestBody = RequestBody.create(MediaType.parse(guessMimeType(file.getName())), file);
      builder.addFormDataPart(fileKey, file.getName(), requestBody);
    }

    builder.setType(MultipartBody.FORM);
    MultipartBody multipartBody = builder.build();
    return multipartBody;
  }

  /**
   * 用于把File对象转化成MultipartBody
   *
   * @param file 文件
   * @param fileKey 和后台约定的文件key
   * @return MultipartBody 对象
   * @author long
   * create at 2017/8/16 上午8:07
   */
  public static MultipartBody filesToMultipartBody(File file, String fileKey) {

    MultipartBody.Builder builder = new MultipartBody.Builder();
    RequestBody requestBody = RequestBody.create(MediaType.parse(guessMimeType(file.getName())), file);
    builder.addFormDataPart(fileKey, file.getName(), requestBody);
    builder.setType(MultipartBody.FORM);
    MultipartBody multipartBody = builder.build();
    return multipartBody;
  }

  /**
   * 用于把File对象转化成MultipartBody(有参数)
   *
   * @param file 文件
   * @param fileKey 和后台约定的文件key
   * @return MultipartBody 对象
   * @author long
   * create at 2017/8/16 上午8:07
   */
  public static MultipartBody filesToMultipartBody(File file, String fileKey, Map<String, String> params) {

    MultipartBody.Builder builder = new MultipartBody.Builder();

    addParams(builder, params);

    RequestBody requestBody = RequestBody.create(MediaType.parse(guessMimeType(file.getName())), file);
    builder.addFormDataPart(fileKey, file.getName(), requestBody);

    builder.setType(MultipartBody.FORM);
    MultipartBody multipartBody = builder.build();
    return multipartBody;
  }


  /**
   * 用于把多个File对象转化成MultipartBodyPart
   *
   * @param files 文件集合
   * @param fileKey 和后台约定的文件key
   * @return MultipartBodyPart
   * 对象集合
   * @author long
   * create at 2017/8/16 上午8:07
   */
  public static List<MultipartBody.Part> filesToMultipartBodyParts(List<File> files, String fileKey) {

    List<MultipartBody.Part> parts = new ArrayList<>(files.size());

    for (File file : files) {
      RequestBody requestBody = RequestBody.create(MediaType.parse(guessMimeType(file.getName())), file);
      MultipartBody.Part part = MultipartBody.Part.createFormData(fileKey, file.getName(), requestBody);
      parts.add(part);
    }
    return parts;
  }

  /**
   * 商汤解析接口参数转换
   *
   * @param params 参数
   * @return MultipartBody
   */
  public static MultipartBody getRequestPOSTPara(Map<String, Object> params) {
    MultipartBody multipartBody = null;
    if (params != null) {
      MultipartBody.Builder mulBuilder = new MultipartBody.Builder();
      for (String key : params.keySet()) {
        if (params.get(key) != null) {
          if (params.get(key) instanceof String) {
            mulBuilder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + key + "\""),
                RequestBody.create(MediaType.parse("text/plain; charset=UTF-8"), (String) params.get(key)));
          } else if (params.get(key) instanceof Integer) {
            mulBuilder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + key + "\""),
                RequestBody.create(MediaType.parse("text/plain; charset=UTF-8"), String.valueOf(params.get(key))));
          } else if (params.get(key) instanceof byte[]) {
            RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), (byte[]) params.get(key));
            mulBuilder.addFormDataPart(key, key, fileBody);
          }
        }
      }
      mulBuilder.setType(MultipartBody.FORM);
      multipartBody = mulBuilder.build();
    }
    return multipartBody;
  }

  private static String guessMimeType(String path) {
    //尝试通过文件名获取文件类型
    FileNameMap fileNameMap = URLConnection.getFileNameMap();
    String contentTypeFor = null;
    try {
      contentTypeFor = fileNameMap.getContentTypeFor(URLEncoder.encode(path, "UTF-8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    if (contentTypeFor == null) {
      //当没有获取到文件类型的时候,使用"application/octet-stream"类型上传文件
      contentTypeFor = "application/octet-stream";
    }
    return contentTypeFor;
  }

  private static void addParams(MultipartBody.Builder builder, Map<String, String> params) {
    if (params != null && !params.isEmpty()) {
      for (String key : params.keySet()) {
        builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + key + "\""),
            RequestBody.create(null, params.get(key)));
      }
    }
  }
}
