package ada.edu.demo.webtest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(Exception.class)
    public ModelAndView handle400Errors(Exception ex) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/errorpages/error_general");
        mv.addObject("exception", ex.getMessage());
        return mv;
    }


    @ExceptionHandler(StudentException.class)
    public ModelAndView handle500Errors(Exception ex) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/errorpages/error_student");
        mv.addObject("exception", ex.getMessage());
        return mv;
    }

}
