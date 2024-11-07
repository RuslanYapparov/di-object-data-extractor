package ru.yappy.rzdengineerassistant.threeclickdoc.docdatadtos.protocol.failure;

/**
 * Класс DTO для хранения и передачи информации, касающейся расследования и заключений о причинах отказа.
 *
 * @param characterJustification обоснование присвоения характера отказа
 * @param description описание отказа - информация о том, как проявилось нарушение нормальной работы отказавшего
 *                    технического средства
 * @param cause непосредственная причина отказа - что привело к случившемуся нарушению нормальной работы
 *             (например, конкретная неисправность какого-либо элемента или сверхнормативное значение какой-либо
 *             характеристики)
 * @param subCause подпричина отказа - какое несоответствие или неисправность привели к ситуации, когда причина отказа
 *                 привела к его появлению
 */
public record FailureReasoningInfoDto(String description,
                                      String characterJustification,
                                      String cause,
                                      String subCause) {}