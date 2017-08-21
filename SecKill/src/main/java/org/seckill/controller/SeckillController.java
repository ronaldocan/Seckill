package org.seckill.controller;

import org.seckill.common.SeckillEnum;
import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccessKilledDao;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.dto.SeckillResult;
import org.seckill.dto.User;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.validator.UserValidator;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/17.
 */
@Controller
@RequestMapping("/seckill")
//@SessionAttributes(value = {"users","managers"},types = {User.class,Seckill.class})
public class SeckillController {
    @Autowired
    private SeckillService seckillService;
    @Autowired
    private UserValidator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(validator);
        binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat("yyyy-MM-DD"),false));
        binder.registerCustomEditor(Integer.class,new PropertyEditorSupport(){
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (text == null || text.equals("")) {
                    text = "0";
                }
                setValue(Integer.parseInt(text));
            }

            @Override
            public String getAsText() {
                return getValue().toString();
            }
        });
        binder.registerCustomEditor(Double.class,new PropertyEditorSupport(){
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if(text == null || text.equals(""))
                    text = "0";
                setValue(Double.parseDouble(text));
            }

            @Override
            public String getAsText() {
                return getValue().toString();
            }
        });
    }
//    @RequestMapping(value = "/sign", method = RequestMethod.GET)
//    public String signUp(@Validated User user, BindingResult r,) {
//        List<Seckill> seckills = seckillService.getSeckillList();
//        model.addAttribute("seckillList", seckills);
//        return "list";
//    }
    @RequestMapping(value = {"/students/{name}"},method = RequestMethod.GET)
    public String getInfo(@PathVariable(value = "name") String name,@MatrixVariable(required = false)String gender){
//        name = liming,man menace
        return "";
    }
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getSeckillList(Model model) {
        List<Seckill> seckills = seckillService.getSeckillList();
        model.addAttribute("seckillList", seckills);
        return "list";
    }

    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String getSeckillBy(@PathVariable("seckillId") Long seckillId, Model model) {
        if (seckillId == null) {
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.getById(seckillId);
        if (seckill == null) {
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill", seckill);
        return "detail";
    }

    @RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> exposerSeckill(@PathVariable("seckillId") Long seckillId) {
        SeckillResult result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult(true, exposer);
        } catch (Exception e) {
            e.printStackTrace();
            result = new SeckillResult(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/{seckillId}/{md5Url}/execution", method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult execute(@PathVariable("seckillId") Long seckillId, @PathVariable("md5Url") String md5Url,
                                 @CookieValue(value = "phone",required = false)Integer phone) {
        if(phone == null){
            return new SeckillResult(false, "用户不存在");
        }
        SeckillResult result;
        try {
            SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, md5Url, phone);
            return new SeckillResult(true, seckillExecution);
        } catch (RepeatKillException e) {
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillEnum.REPEATE_KILL);
            return new SeckillResult(true, seckillExecution);
        } catch (SeckillCloseException e) {
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillEnum.END);
            return new SeckillResult(true, seckillExecution);
        } catch (SeckillException e) {
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillEnum.DATA_REWRITE);
            return new SeckillResult(false, seckillExecution);
        } catch (Exception e) {
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillEnum.INNER_ERROR);
            return new SeckillResult(false, seckillExecution);
        }

    }
    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> time(){
        Date now = new Date();
        return new SeckillResult<Long>(true,now.getTime());
    }
}
