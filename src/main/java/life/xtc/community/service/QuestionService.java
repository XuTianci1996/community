package life.xtc.community.service;

import life.xtc.community.dto.QuestionDTO;
import life.xtc.community.entity.Question;
import life.xtc.community.entity.User;
import life.xtc.community.mapper.QuestionMapper;
import life.xtc.community.mapper.UserMapper;
import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public List<QuestionDTO> list() {
        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for(Question question:questions ){
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            System.out.println(user.getId());
            questionDTOS.add(questionDTO);
        }
        return questionDTOS;
    }
}