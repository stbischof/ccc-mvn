package org.congocc.maven.plugin;

import java.io.File;
import java.util.Map;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

@Mojo(name = "ccc-generate", defaultPhase = LifecyclePhase.GENERATE_SOURCES, requiresProject = true, requiresDependencyResolution = ResolutionScope.NONE)
public class GeneratorMojo extends AbstractMojo {


    /**
     * The current Maven project.
     *
     */
    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    private MavenProject project;
    
    @Parameter(property = "grammarFile", defaultValue = "${basedir}/src/main/ccc/Grammer.ccc")
    File grammarFile;
    @Parameter(property = "outputDir", defaultValue = "${project.build.directory}/generated-sources/ccc")

    File outputDir;
    @Parameter(property = "lang", defaultValue = "java")
    String codeLang;
    @Parameter(property = "jdk", defaultValue = "8")
    int jdkTarget;
    @Parameter(property = "quiet", defaultValue = "false")

    boolean quiet;
    Map<String, String> symbols;

    public void execute() throws MojoExecutionException, MojoFailureException {
        try {

            getLog().warn("a");
            getLog().warn("a");

            int returnValue = org.congocc.app.Main.mainProgram(grammarFile.toPath(), outputDir.toPath(), codeLang, jdkTarget, quiet, symbols);
       
            this.project.addCompileSourceRoot( outputDir.getAbsolutePath() );
            getLog().warn("return:" + returnValue);

        } catch (Exception e) {
            getLog().error(e);

            e.printStackTrace();
        }
    }
}
