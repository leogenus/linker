package uz.kvikk.linker.resource

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable


@Controller
class LinkResource {
    @GetMapping("/")
    fun test(model: Model): String {
        model.addAttribute("test", "Test text!")
        return "index"
    }

    @GetMapping("/{link}")
    fun link(@PathVariable link: String, model: Model): String {
        model.addAttribute("link", link)
        return "link"
    }
}
