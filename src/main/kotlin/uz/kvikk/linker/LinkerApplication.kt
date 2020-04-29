package uz.kvikk.linker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LinkerApplication

fun main(args: Array<String>) {
	runApplication<LinkerApplication>(*args)
}

/** todo index.html yasash kerak
 * <!DOCTYPE HTML>
<html lang="en-US">
<head>
<meta charset="UTF-8">

<script>
// Empty
</script>

<script type="text/javascript">
window.location.href = "https://play.google.com/store/apps/details?id=com.dimasta.feelqueen"
</script>
<title>Redirecting...</title>
</head>
<body>
<p>If you are not redirected automatically, follow this <a href="https://play.google.com/store/apps/details?id=com.dimasta.feelqueen">link to redirect</a>.</p>
</body>
</html>
 */
