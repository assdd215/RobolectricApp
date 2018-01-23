package com.example.luzeping_sx.robolectricapp.robolectric;

import org.junit.runners.model.InitializationError;
import org.robolectric.RoboSettings;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.manifest.AndroidManifest;
import org.robolectric.res.Fs;
import org.robolectric.res.ResourcePath;

import java.util.List;

/**
 * Created by aria on 2018/1/23.
 */

public class MyRoboRunner extends RobolectricTestRunner{
    /**
     * Creates a runner to run {@code testClass}. Looks in your working directory for your AndroidManifest.xml file
     * and res directory by default. Use the {@link Config} annotation to configure.
     *
     * @param testClass the test class to be run
     * @throws InitializationError if junit says so
     */
    public MyRoboRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
        // 从源码知道MavenDependencyResolver默认以RoboSettings的repositoryUrl 
        // 和repositoryId为默认值，因此只需要对RoboSetting进行赋值即可 
        RoboSettings.setMavenRepositoryId("alimaven");
        RoboSettings.setMavenRepositoryUrl("http://maven.aliyun.com/nexus/content/groups/public/");

    }

    @Override
    protected AndroidManifest getAppManifest(Config config) {
        return super.getAppManifest(config);


//        //TODO 如果测试框架找不到工程的这几个文件 可以用绝对路径去指定它们。暂时还没有更好的解决方案。
//        String appRoot = "/../";
//        String resDir = appRoot + "build/intermediates/res/merged/debug/";
//        String assetDirt = appRoot + "build/intermediates/assets/debug/";
//
//        return new AndroidManifest(Fs.fileFromPath("/Users/aria/AndroidStudioProjects/liveshow-android/GameLive/test/AndroidManifest.xml"),
//                Fs.fileFromPath(resDir),Fs.fileFromPath(assetDirt)){
//            @Override
//            public List<ResourcePath> getIncludedResourcePaths() {
//                List<ResourcePath> paths = super.getIncludedResourcePaths();
//                return paths;
//            }
//        };

    }
}
