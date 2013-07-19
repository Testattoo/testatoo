/**
 * @author David Avenante (d.avenante@gmail.com)
 */
import com.petebevin.markdown.MarkdownProcessor
import groovy.text.SimpleTemplateEngine
import org.apache.commons.io.FileUtils

import java.nio.charset.Charset

// params (-DsrcDir=manual -DoutputDir=target/manual)
def srcDir = (binding.hasVariable('project') ? project.properties['srcDir'] as String : System.getProperty('srcDir')) as File
def outputDir = (binding.hasVariable('project') ? project.properties['outputDir'] as String : System.getProperty('outputDir')) as File

// script
def templates = new File(srcDir, 'templates')
def documents = new File(srcDir, 'chapters')
def HTMLTemplate = new File(templates, "manual.html")

def templateEngine = new SimpleTemplateEngine()
def processor = new MarkdownProcessor()

outputDir.deleteDir()
outputDir.mkdir()

HTMLTarget = new File(outputDir, "html")
HTMLTarget.mkdir()
FileUtils.copyDirectory(srcDir, HTMLTarget, {it != templates && it != documents} as FileFilter)

documents.eachFile {File document ->
    def HTMLContent = new StringBuilder()
    def HTMLToc = new StringBuilder()
    def headingStack = []
    def previousLevel = 0

    document.eachLine { line ->
        def matcher = line =~ /^(#+)\s+(.*)$/
        if (matcher) {
            def heading = [title: matcher[0][2], level: matcher[0][1].size()]
            if (headingStack.size() < heading.level) {
                headingStack.push(0)
            } else {
                while (headingStack.size() > heading.level) {
                    headingStack.pop()
                }
            }
            headingStack.push(headingStack.pop() + 1)
            def prefix = headingStack.join('.')
            def id = heading.title.replaceAll('[- \\/_]+', '_').replaceAll('[^a-zA-Z0-9_]+', '').toLowerCase()

            HTMLContent << "${'#' * heading.level}<span id=\"${id}\">${prefix}</span>${heading.title}"
            if (heading.level > previousLevel) {
                HTMLToc << "<ul><li>"
            } else if (heading.level == previousLevel) {
                HTMLToc << "</li><li>"
            } else {
                HTMLToc << "</li></ul></li><li>" * (previousLevel - heading.level)
            }

            HTMLToc << "<span class=\"toc_number\">${prefix}</span><a href=\"#${id}\">${heading.title}</a>\n"
            previousLevel = heading.level;
        } else {
            HTMLContent << line
        }
        HTMLContent << "\n"
    }

    def documentName = document.name.split('[.]')[0]

    new File(HTMLTarget, documentName + ".html") << templateEngine.createTemplate(HTMLTemplate.getText(Charset.defaultCharset().name())).make(document: [
            content: processor.markdown(HTMLContent.toString()),
            toc: HTMLToc
    ])
}