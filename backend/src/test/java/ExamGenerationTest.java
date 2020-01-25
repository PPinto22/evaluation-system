//import db.PopulateDB;
//import exception.ExistentEntityException;
//import exception.InsufficientQuestionsException;
//import exception.InvalidInputException;
//import exception.NonExistentEntityException;
//import model.persistent.Class;
//import model.persistent.Group;
//import model.persistent.Question;
//import model.persistent.Teacher;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.orm.PersistentException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import service.ClassService;
//import service.GroupService;
//import service.TeacherService;
//import service.UserService;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {AppConfig.class})
//public class ExamGenerationTest {
//
//    @Autowired
//    private GroupService groupService;
//
//    private Group group;
//    private List<String> valid_categories = new ArrayList<>();
//    private List<Integer> valid_difficulties = new ArrayList<>();
//
//    private List<String> invalid_categories = new ArrayList<>();
//    private List<Integer> invalid_difficulties = new ArrayList<>();
//
//    @Before
//    public void setupCategoriesAndDifficulties() throws Exception {
//        group = groupService.getGroupByID(1);
//        for(int i = 0; i<PopulateDB.N_QUESTIONS_CLASS; i++){
//            int category_i = i%PopulateDB.N_CATEGORIES + 1;
//            String category = "Category"+category_i;
//            int difficulty = i%3 + 1;
//            valid_categories.add(category);
//            valid_difficulties.add(difficulty);
//        }
//        // Dados invalidos pedem mais uma questao do que o total de questoes registadas
//        // Deve atirar excecao InsuficientQuestionsException
//        invalid_categories = new ArrayList<>(valid_categories);
//        invalid_difficulties = new ArrayList<>(valid_difficulties);
//        invalid_categories.add("Category1");
//        invalid_difficulties.add(1);
//    }
//
//    @Test
//    public void generateExam(){
//        List<Question> questions = null;
//        try{
//            questions = groupService.generateExamQuestions(group, valid_categories, valid_difficulties);
//        } catch (Exception e){
//            Assert.assertTrue(false);
//        }
//
//        try{
//            groupService.generateExamQuestions(group, invalid_categories, invalid_difficulties);
//        } catch (InsufficientQuestionsException e) {
//            Assert.assertTrue(true);
//        } catch (Exception e){
//            Assert.assertTrue(false);
//        }
//    }
//
//}
