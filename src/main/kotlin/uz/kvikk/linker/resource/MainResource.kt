package uz.kvikk.linker.resource

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import uz.kvikk.linker.service.LinkService


@Controller
class MainResource(private val linkService: LinkService) {
    @GetMapping("/")
    fun test(model: Model): String {
        model.addAttribute("test", "Test text!")
        return "index"
    }

    @GetMapping("/{hashId}")
    fun link(@PathVariable hashId: String, model: Model): String {
        if (hashId.isEmpty())
            throw RuntimeException("Short link should not be empty! ")

        val linkView = linkService.oneByHashId(hashId)
        return if (linkView != null) {
            val url = linkView.url!!
            val link = if (
                    url.startsWith("http://") ||
                    url.startsWith("https://"))
                url
            else
                "http://${url}"
            "redirect:$link"
        } else {
            model.addAttribute("link", hashId)
            "index"
        }
    }

}
