package de.swm.example.web;

import de.swm.example.web.model.TimestampDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO (KS) Dokumentieren.
 *
 * @author steuer.konstantin <br>
 * copyright (C) 2015, SWM Services GmbH
 */
@Controller
@RequestMapping(value = CommonTimestamp.MAIN_PATH)
public class TimestampWebController extends CommonTimestamp {

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.TEXT_HTML_VALUE)
	public void create(HttpServletResponse httpResponse) throws IOException {
		TimestampDTO created = createTimestamp();
		httpResponse.sendRedirect(CommonTimestamp.MAIN_PATH + "/" + created.getId());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String findOne(@PathVariable Long id, Model model) {
		model.addAttribute("timestamp", findById(id));
		return "timestamp";
	}


}
