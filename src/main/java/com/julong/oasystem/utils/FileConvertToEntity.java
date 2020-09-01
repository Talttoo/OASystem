package com.julong.oasystem.utils;

import com.julong.oasystem.entity.view.AddPaperViewPaper;
import com.julong.oasystem.entity.view.AddPaperViewQuestion;
import com.julong.oasystem.entity.wage.InputWageItemVO;
import com.julong.oasystem.entity.wage.InputWageVO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/*
 * Author:
 * Email :
 *
 */
@Service
public class FileConvertToEntity {
    /**
     * Excel转换成问卷实体类
     * @param file
     * @return
     */
    public AddPaperViewPaper convert(MultipartFile file) {
        AddPaperViewPaper paper = new AddPaperViewPaper();
        try {
            paper.setStartTime(null);
            paper.setEndTime(null);
            paper.setStatus(0);
            List<AddPaperViewQuestion> questions = new ArrayList<>();
            HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            int rowNumber = sheet.getPhysicalNumberOfRows();
            for (int i = 0; i < rowNumber; i++) {
                Row row = sheet.getRow(i);
                AddPaperViewQuestion question = new AddPaperViewQuestion();
                List<String> options = new ArrayList<>();

                for (int j = 0; j < row.getPhysicalNumberOfCells() && j < 10; j++) {
                    row.getCell(j).setCellType(CellType.STRING);
                    String value = row.getCell(j).getStringCellValue();
                    if (i == 0) {   //set title
                        paper.setTitle(value);
                        break;
                    } else if (i == 1) {    //info text
                        break;
                    } else {  //questions
                        if (j == 0) {
                            question.setQuestionType(Integer.parseInt(value));
                        } else if (j == 1) {
                            question.setQuestionTitle(value);
                        } else {
                            options.add(value);
                        }
                    }
                    //System.out.print(row.getCell(j).getStringCellValue() + "\t");
                }
                if (i > 1) {
                    question.setQuestionOption(options);
                    questions.add(question);
                }

            }
            paper.setQuestions(questions);
        } catch (Exception e) {
            return null;
        }
        return paper;
    }

    /**
     * Excel转换成工资实体类
     * @param file
     * @return
     */
    public InputWageVO convert2Wage(MultipartFile file) {
        InputWageVO wage = new InputWageVO();
        List<String> wageColumn = new ArrayList<>();
        try {
            wage.setCreateTime(null);
            wage.setStatus(0);
            List<InputWageItemVO> inputWageItemVOS = new ArrayList<>();
            HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);
            int rowNumber = sheet.getPhysicalNumberOfRows();
            for (int i = 0; i < rowNumber; i++) {
                Row row = sheet.getRow(i);
                InputWageItemVO inputWageItemVO = new InputWageItemVO();

                List<String> options = new ArrayList<>();

                for (int j = 0; j < row.getPhysicalNumberOfCells() && j < 12; j++) {
                    row.getCell(j).setCellType(CellType.STRING);
                    String value = row.getCell(j).getStringCellValue();
                    if (i == 0) {   //工资月份
                        wage.setWageMonth(value);
                        break;
                    } else if (i == 1) {    //工资栏目
                        wageColumn.add(value);
                    } else {  //工资详情
                        if (j == 0) {
                            inputWageItemVO.setWageEmployee(value);

                        } else if (j == 1) {
                            inputWageItemVO.setWageEmployeeDept(value);
                        } else {
                            options.add(value);
                        }
                    }
                    //System.out.print(row.getCell(j).getStringCellValue() + "\t");
                }
                if (i > 1) {
                    inputWageItemVO.setWageDetails(options);
                    inputWageItemVOS.add(inputWageItemVO);
                }

            }
            wage.setWageColumn(wageColumn);
            wage.setWageItem(inputWageItemVOS);
        } catch (Exception e) {
            return null;
        }
        return wage;
    }

}
