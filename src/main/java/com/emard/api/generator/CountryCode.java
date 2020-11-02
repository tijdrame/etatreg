/*
 * Copyright (C) 2012 Neo Visionaries Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.emard.api.generator;

import java.util.HashMap;
import java.util.Map;


/**
 * <a href="http://en.wikipedia.org/wiki/ISO_3166-1">ISO 3166-1</a>
 * country code.
 *
 * <p>
 * Enum names of this enum themselves are represented by
 * <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha-2</a>
 * codes. There are instance methods to get the country name ({@link #getName()}), the
 * <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3" >ISO 3166-1 alpha-3</a>
 * code ({@link #getAlpha3()}) and the
 * <a href="http://en.wikipedia.org/wiki/ISO_3166-1_numeric">ISO 3166-1 numeric</a>
 * code ({@link #getNumeric()}).
 * In addition, there are static methods to get a CountryCode instance that
 * corresponds to a given alpha-2/alpha-3/numeric code ({@link #getByCode(String)},
 * {@link #getByCode(int)}).
 * </p>
 *
 * <pre style="background-color: #EEEEEE; margin-left: 2em; margin-right: 2em; border: 1px solid black;">
 * <span style="color: darkgreen;">// EXAMPLE</span>
 *
 * CountryCode cc = CountryCode.{@link #getByCode(String) getByCode}("JP");
 *
 * <span style="color: darkgreen;">// Country name</span>
 * System.out.println("Country name = " + cc.{@link #getName()});                  <span style="color: darkgreen;">// "Japan"</span>
 *
 * <span style="color: darkgreen;">// ISO 3166-1 alpha-2 code</span>
 * System.out.println("ISO 3166-1 alpha-2 code = " + cc.{@link #getAlpha2()});     <span style="color: darkgreen;">// "JP"</span>
 *
 * <span style="color: darkgreen;">// ISO 3166-1 alpha-3 code</span>
 * System.out.println("ISO 3166-1 alpha-3 code = " + cc.{@link #getAlpha3()});     <span style="color: darkgreen;">// "JPN"</span>
 *
 * <span style="color: darkgreen;">// ISO 3166-1 numeric code</span>
 * System.out.println("ISO 3166-1 numeric code = " + cc.{@link #getNumeric()});    <span style="color: darkgreen;">// 392</span>
 * </pre>
 *
 * @author Takahiko Kawasaki
 */
public enum CountryCode
{
    // @formatter:off
    /** <a href="http://en.wikipedia.org/wiki/Andorra">Andorra</a> */
    AD("Andorre", "AND", 16),

    /** <a href="http://en.wikipedia.org/wiki/United_Arab_Emirates">United Arab Emirates</a> */
    AE("Emirats Arabes Unis", "ARE", 784),

    /** <a href="http://en.wikipedia.org/wiki/Afghanistan">Afghanistan</a> */
    AF("Afghanistan", "AFG", 4),

    /** <a href="http://en.wikipedia.org/wiki/Antigua_and_Barbuda">Antigua and Barbuda</a> */
    AG("Antigua-et-Barbuda", "ATG", 28),

    /** <a href="http://en.wikipedia.org/wiki/Anguilla">Anguilla</a> */
    AI("Anguilla", "AIA", 660),

    /** <a href="http://en.wikipedia.org/wiki/Albania">Albania</a> */
    AL("Albanie", "ALB", 8),

    /** <a href="http://en.wikipedia.org/wiki/Armenia">Armenia</a> */
    AM("Armenie", "ARM", 51),

    /** <a href="http://en.wikipedia.org/wiki/Netherlands_Antilles">Netherlands Antilles</a> */
    AN("Antilles néerlandaises", "ANT", 530),

    /** <a href="http://en.wikipedia.org/wiki/Angola">Angola</a> */
    AO("Angola", "AGO", 24),

    /** <a href="http://en.wikipedia.org/wiki/Antarctica">Antarctica</a> */
    AQ("Antarctique", "ATA", 10),

    /** <a href="http://en.wikipedia.org/wiki/Argentina">Argentina</a> */
    AR("Argentine", "ARG", 32),

    /** <a href="http://en.wikipedia.org/wiki/American_Samoa">American Samoa</a> */
    AS("Samoa américaines", "ASM", 16),

    /** <a href="http://en.wikipedia.org/wiki/Austria">Austria</a> */
    AT("Autriche", "AUT", 40),

    /** <a href="http://en.wikipedia.org/wiki/Australia">Australia</a> */
    AU("Australie", "AUS", 36),

    /** <a href="http://en.wikipedia.org/wiki/Aruba">Aruba</a> */
    AW("Aruba", "ABW", 533),

    /** <a href="http://en.wikipedia.org/wiki/%C3%85land_Islands">&Aring;land Islands</a> */
    AX("Iles Aland", "ALA", 248),

    /** <a href="http://en.wikipedia.org/wiki/Azerbaijan">Azerbaijan</a> */
    AZ("Azerbaidjan", "AZE", 31),

    /** <a href="http://en.wikipedia.org/wiki/Bosnia_and_Herzegovina">Bosnia and Herzegovina</a> */
    BA("Bosnie-Herzegovine", "BIH", 70),

    /** <a href="http://en.wikipedia.org/wiki/Barbados">Barbados</a> */
    BB("Barbades", "BRB", 52),

    /** <a href="http://en.wikipedia.org/wiki/Bangladesh">Bangladesh</a> */
    BD("Bangladesh", "BGD", 50),

    /** <a href="http://en.wikipedia.org/wiki/Belgium">Belgium</a> */
    BE("Belgique", "BEL", 56),

    /** <a href="http://en.wikipedia.org/wiki/Burkina_Faso">Burkina Faso</a> */
    BF("Burkina Faso", "BFA", 854),

    /** <a href="http://en.wikipedia.org/wiki/Bulgaria">Bulgaria</a> */
    BG("Bulgarie", "BGR", 100),

    /** <a href="http://en.wikipedia.org/wiki/Bahrain">Bahrain</a> */
    BH("Bahrein", "BHR", 48),

    /** <a href="http://en.wikipedia.org/wiki/Burundi">Burundi</a> */
    BI("Burundi", "BDI", 108),

    /** <a href="http://en.wikipedia.org/wiki/Benin">Benin</a> */
    BJ("Benin", "BEN", 204),

    /** <a href="http://en.wikipedia.org/wiki/Saint_Barth%C3%A9lemy">Saint Barth&eacute;lemy</a> */
    BL("Saint Barth\u00E9lemy", "BLM", 652),

    /** <a href="http://en.wikipedia.org/wiki/Bermuda">Bermuda</a> */
    BM("Bermudes", "BMU", 60),

    /** <a href="http://en.wikipedia.org/wiki/Brunei">Brunei Darussalam</a> */
    BN("Brunei", "BRN", 96),

    /** <a href="http://en.wikipedia.org/wiki/Bolivia">Plurinational State of Bolivia</a> */
    BO("Bolivie", "BOL", 68),

    /** <a href="http://en.wikipedia.org/wiki/Caribbean_Netherlands">Bonaire, Sint Eustatius and Saba</a> */
    BQ("Saba, Saint-Eustache et Bonaire", "BES", 535),

    /** <a href="http://en.wikipedia.org/wiki/Brazil">Brazil</a> */
    BR("Bresil", "BRA", 76),

    /** <a href="http://en.wikipedia.org/wiki/The_Bahamas">Bahamas</a> */
    BS("Bahamas", "BHS", 44),

    /** <a href="http://en.wikipedia.org/wiki/Bhutan">Bhutan</a> */
    BT("Bhoutan", "BTN", 64),

    /** <a href="http://en.wikipedia.org/wiki/Bouvet_Island">Bouvet Island</a> */
    BV("Ile Bouvet", "BVT", 74),

    /** <a href="http://en.wikipedia.org/wiki/Botswana">Botswana</a> */
    BW("Botswana", "BWA", 72),

    /** <a href="http://en.wikipedia.org/wiki/Belarus">Belarus</a> */
    BY("Bielorussie", "BLR", 112),

    /** <a href="http://en.wikipedia.org/wiki/Belize">Belize</a> */
    BZ("Belize", "BLZ", 84),

    /** <a href="http://en.wikipedia.org/wiki/Canada">Canada</a> */
    CA("Canada", "CAN", 124),

    /** <a href="http://en.wikipedia.org/wiki/Cocos_(Keeling)_Islands">Cocos (Keeling) Islands</a> */
    CC("Iles Cocos", "CCK", 166),

    /** <a href="http://en.wikipedia.org/wiki/Democratic_Republic_of_the_Congo">The Democratic Republic of the Congo</a> */
    CD("Republique démocratique du Congo", "COD", 180),

    /** <a href="http://en.wikipedia.org/wiki/Central_African_Republic">Central African Republic</a> */
    CF("République centrafricaine", "CAF", 140),

    /** <a href="http://en.wikipedia.org/wiki/Republic_of_the_Congo">Congo</a> */
    CG("Congo", "COG", 178),

    /** <a href="http://en.wikipedia.org/wiki/Switzerland">Switzerland</a> */
    CH("Suisse", "CHE", 756),

    /** <a href="http://en.wikipedia.org/wiki/C%C3%B4te_d%27Ivoire">C&ocirc;te d'Ivoire</a> */
    CI("C\u00F4te d'Ivoire", "CIV", 384),

    /** <a href="http://en.wikipedia.org/wiki/Cook_Islands">Cook Islands</a> */
    CK("Iles Cook", "COK", 184),

    /** <a href="http://en.wikipedia.org/wiki/Chile">Chile</a> */
    CL("Chili", "CHL", 152),

    /** <a href="http://en.wikipedia.org/wiki/Cameroon">Cameroon</a> */
    CM("Cameroun", "CMR", 120),

    /** <a href="http://en.wikipedia.org/wiki/China">China</a> */
    CN("Chine", "CHN", 156),

    /** <a href="http://en.wikipedia.org/wiki/Colombia">Colombia</a> */
    CO("Colombie", "COL", 170),

    /** <a href="http://en.wikipedia.org/wiki/Costa_Rica">Costa Rica</a> */
    CR("Costa Rica", "CRI", 188),

    /** <a href="http://en.wikipedia.org/wiki/Cuba">Cuba</a> */
    CU("Cuba", "CUB", 192),

    /** <a href="http://en.wikipedia.org/wiki/Cape_Verde">Cape Verde</a> */
    CV("Cap Vert", "CPV", 132),

    /** <a href="http://en.wikipedia.org/wiki/Cura%C3%A7ao">Cura&ccedil;ao</a> */
    CW("Cura/u00E7ao", "CUW", 531),

    /** <a href="http://en.wikipedia.org/wiki/Christmas_Island">Christmas Island</a> */
    CX("Iles Christmas", "CXR", 162),

    /** <a href="http://en.wikipedia.org/wiki/Cyprus">Cyprus</a> */
    CY("Chypre", "CYP", 196),

    /** <a href="http://en.wikipedia.org/wiki/Czech_Republic">Czech Republic</a> */
    CZ("Republique Tcheque", "CZE", 203),

    /** <a href="http://en.wikipedia.org/wiki/Germany">Germany</a> */
    DE("Allemagne", "DEU", 276),

    /** <a href="http://en.wikipedia.org/wiki/Djibouti">Djibouti </a> */
    DJ("Djibouti", "DJI", 262),

    /** <a href="http://en.wikipedia.org/wiki/Denmark">Denmark</a> */
    DK("Danemark", "DNK", 208),

    /** <a href="http://en.wikipedia.org/wiki/Dominica">Dominica</a> */
    DM("Dominique", "DMA", 212),

    /** <a href="http://en.wikipedia.org/wiki/Dominican_Republic">Dominican Republic</a> */
    DO("Republique Dominicaine", "DOM", 214),

    /** <a href="http://en.wikipedia.org/wiki/Algeria">Algeria</a> */
    DZ("Algerie", "DZA", 12),

    /** <a href="http://en.wikipedia.org/wiki/Ecuador">Ecuador</a> */
    EC("Equateur", "ECU", 218),

    /** <a href="http://en.wikipedia.org/wiki/Estonia">Estonia</a> */
    EE("Estonie", "EST", 233),

    /** <a href="http://en.wikipedia.org/wiki/Egypt">Egypt</a> */
    EG("Egypte", "EGY", 818),

    /** <a href="http://en.wikipedia.org/wiki/Western_Sahara">Western Sahara</a> */
    EH("Sahara occidental", "ESH", 732),

    /** <a href="http://en.wikipedia.org/wiki/Eritrea">Eritrea</a> */
    ER("Erythree", "ERI", 232),

    /** <a href="http://en.wikipedia.org/wiki/Spain">Spain</a> */
    ES("Espagne", "ESP", 724),

    /** <a href="http://en.wikipedia.org/wiki/Ethiopia">Ethiopia</a> */
    ET("Ethiopie", "ETH", 231),

    /** <a href="http://en.wikipedia.org/wiki/Finland">Finland</a> */
    FI("Finlande", "FIN", 246),

    /** <a href="http://en.wikipedia.org/wiki/Fiji">Fiji</a> */
    FJ("Fiji", "FJI", 242),

    /** <a href="http://en.wikipedia.org/wiki/Falkland_Islands">Falkland Islands (Malvinas)</a> */
    FK("Iles Falkland", "FLK", 238),

    /** <a href="http://en.wikipedia.org/wiki/Federated_States_of_Micronesia">Federated States of Micronesia</a> */
    FM("Micronesie", "FSM", 583),

    /** <a href="http://en.wikipedia.org/wiki/Faroe_Islands">Faroe Islands</a> */
    FO("Iles Feroe", "FRO", 234),

    /** <a href="http://en.wikipedia.org/wiki/France">France</a> */
    FR("France", "FRA", 250),

    /** <a href="http://en.wikipedia.org/wiki/Gabon">Gabon </a> */
    GA("Gabon", "GAB", 266),

    /** <a href="http://en.wikipedia.org/wiki/United_Kingdom">United Kingdom</a> */
    GB("Royaume-Uni", "GBR", 826),

    /** <a href="http://en.wikipedia.org/wiki/Grenada">Grenada</a> */
    GD("Grenade", "GRD", 308),

    /** <a href="http://en.wikipedia.org/wiki/Georgia_(country)">Georgia</a> */
    GE("Etat de Georgie", "GEO", 268),

    /** <a href="http://en.wikipedia.org/wiki/French_Guiana">French Guiana</a> */
    GF("Guyane francaise", "GUF", 254),

    /** <a href="http://en.wikipedia.org/wiki/Guernsey">Guemsey</a> */
    GG("Guernesey", "GGY", 831),

    /** <a href="http://en.wikipedia.org/wiki/Ghana">Ghana</a> */
    GH("Ghana", "GHA", 288),

    /** <a href="http://en.wikipedia.org/wiki/Gibraltar">Gibraltar</a> */
    GI("Gibraltar", "GIB", 292),

    /** <a href="http://en.wikipedia.org/wiki/Greenland">Greenland</a> */
    GL("Groenland", "GRL", 304),

    /** <a href="http://en.wikipedia.org/wiki/The_Gambia">Gambia</a> */
    GM("Gambie", "GMB", 270),

    /** <a href="http://en.wikipedia.org/wiki/Guinea">Guinea</a> */
    GN("Guinee", "GIN", 324),

    /** <a href="http://en.wikipedia.org/wiki/Guadeloupe">Guadeloupe</a> */
    GP("Guadeloupe", "GLP", 312),

    /** <a href="http://en.wikipedia.org/wiki/Equatorial_Guinea">Equatorial Guinea</a> */
    GQ("Guinee Equatorial", "GNQ", 226),

    /** <a href="http://en.wikipedia.org/wiki/Greece">Greece</a> */
    GR("Grece", "GRC", 300),

    /** <a href="http://en.wikipedia.org/wiki/South_Georgia_and_the_South_Sandwich_Islands">South Georgia and the South Sandwich Islands</a> */
    GS("Georgie du Sud et les iles Sandwich", "SGS", 239),

    /** <a href="http://en.wikipedia.org/wiki/Guatemala">Guatemala</a> */
    GT("Guatemala", "GTM", 320),

    /** <a href="http://en.wikipedia.org/wiki/Guam">Guam</a> */
    GU("Guam", "GUM", 316),

    /** <a href="http://en.wikipedia.org/wiki/Guinea-Bissau">Guinea-Bissau</a> */
    GW("Guinee-Bissau", "GNB", 624),

    /** <a href="http://en.wikipedia.org/wiki/Guyana">Guyana</a> */
    GY("Guyane", "GUY", 328),

    /** <a href="http://en.wikipedia.org/wiki/Hong_Kong">Hong Kong</a> */
    HK("Hong Kong", "HKG", 344),

    /** <a href="http://en.wikipedia.org/wiki/Heard_Island_and_McDonald_Islands">Heard Island and McDonald Islands</a> */
    HM("Iles Heard et iles MacDonald", "HMD", 334),

    /** <a href="http://en.wikipedia.org/wiki/Honduras">Honduras</a> */
    HN("Honduras", "HND", 340),

    /** <a href="http://en.wikipedia.org/wiki/Croatia">Croatia</a> */
    HR("Croatie", "HRV", 191),

    /** <a href="http://en.wikipedia.org/wiki/Haiti">Haiti</a> */
    HT("Haiti", "HTI", 332),

    /** <a href="http://en.wikipedia.org/wiki/Hungary">Hungary</a> */
    HU("Hongrie", "HUN", 348),

    /** <a href="http://en.wikipedia.org/wiki/Indonesia">Indonesia</a> */
    ID("Indonesie", "IDN", 360),

    /** <a href="http://en.wikipedia.org/wiki/Republic_of_Ireland">Ireland</a> */
    IE("Irlande", "IRL", 372),

    /** <a href="http://en.wikipedia.org/wiki/Israel">Israel</a> */
    IL("Israel", "ISR", 376),

    /** <a href="http://en.wikipedia.org/wiki/Isle_of_Man">Isle of Man</a> */
    IM("Ile de Man", "IMN", 833),

    /** <a href="http://en.wikipedia.org/wiki/India">India</a> */
    IN("Inde", "IND", 356),

    /** <a href="http://en.wikipedia.org/wiki/British_Indian_Ocean_Territory">British Indian Ocean Territory</a> */
    IO("Territoire Britannique de l Ocean Indien", "IOT", 86),

    /** <a href="http://en.wikipedia.org/wiki/Iraq">Iraq</a> */
    IQ("Iraq", "IRQ", 368),

    /** <a href="http://en.wikipedia.org/wiki/Iran">Islamic Republic of Iran</a> */
    IR("Iran", "IRN", 364),

    /** <a href="http://en.wikipedia.org/wiki/Iceland">Iceland</a> */
    IS("Iselande", "ISL", 352),

    /** <a href="http://en.wikipedia.org/wiki/Italy">Italy</a> */
    IT("Italie", "ITA", 380),

    /** <a href="http://en.wikipedia.org/wiki/Jersey">Jersey</a> */
    JE("Jersey", "JEY", 832),

    /** <a href="http://en.wikipedia.org/wiki/Jamaica">Jamaica</a> */
    JM("Jamaique", "JAM", 388),

    /** <a href="http://en.wikipedia.org/wiki/Jordan">Jordan</a> */
    JO("Jordan", "JOR", 400),

    /** <a href="http://en.wikipedia.org/wiki/Japan">Japan</a> */
    JP("Japon", "JPN", 392),

    /** <a href="http://en.wikipedia.org/wiki/Kenya">Kenya</a> */
    KE("Kenya", "KEN", 404),

    /** <a href="http://en.wikipedia.org/wiki/Kyrgyzstan">Kyrgyzstan</a> */
    KG("Kirghizistan", "KGZ", 417),

    /** <a href="http://en.wikipedia.org/wiki/Cambodia">Cambodia</a> */
    KH("Cambodge", "KHM", 116),

    /** <a href="http://en.wikipedia.org/wiki/Kiribati">Kiribati</a> */
    KI("Kiribati", "KIR", 296),

    /** <a href="http://en.wikipedia.org/wiki/Comoros">Comoros</a> */
    KM("Comores", "COM", 174),

    /** <a href="http://en.wikipedia.org/wiki/Saint_Kitts_and_Nevis">Saint Kitts and Nevis</a> */
    KN("Saint-Kitts-et-Nevis", "KNA", 659),

    /** <a href="http://en.wikipedia.org/wiki/North_Korea">Democratic People's Republic of Korea</a> */
    KP("Coree du Nord", "PRK", 408),

    /** <a href="http://en.wikipedia.org/wiki/South_Korea">Republic of Korea</a> */
    KR("Coree du Sud", "KOR", 410),

    /** <a href="http://en.wikipedia.org/wiki/Kuwait">Kuwait</a> */
    KW("Koweit", "KWT", 414),

    /** <a href="http://en.wikipedia.org/wiki/Cayman_Islands">Cayman Islands</a> */
    KY("Iles Caiman", "CYM", 136),

    /** <a href="http://en.wikipedia.org/wiki/Kazakhstan">Kazakhstan</a> */
    KZ("Kazakhstan", "KAZ", 398),

    /** <a href="http://en.wikipedia.org/wiki/Laos">Lao People's Democratic Republic</a> */
    LA("Laos", "LAO", 418),

    /** <a href="http://en.wikipedia.org/wiki/Lebanon">Lebanon</a> */
    LB("Liban", "LBN", 422),

    /** <a href="http://en.wikipedia.org/wiki/Saint_Lucia">Saint Lucia</a> */
    LC("Saint Lucie", "LCA", 662),

    /** <a href="http://en.wikipedia.org/wiki/Liechtenstein">Liechtenstein</a> */
    LI("Liechtenstein", "LIE", 438),

    /** <a href="http://en.wikipedia.org/wiki/Sri_Lanka">Sri Lanka</a> */
    LK("Sri Lanka", "LKA", 144),

    /** <a href="http://en.wikipedia.org/wiki/Liberia">Liberia</a> */
    LR("Liberia", "LBR", 430),

    /** <a href="http://en.wikipedia.org/wiki/Lesotho">Lesotho</a> */
    LS("Lesotho", "LSO", 426),

    /** <a href="http://en.wikipedia.org/wiki/Lithuania">Lithuania</a> */
    LT("Lituanie", "LTU", 440),

    /** <a href="http://en.wikipedia.org/wiki/Luxembourg">Luxembourg</a> */
    LU("Luxembourg", "LUX", 442),

    /** <a href="http://en.wikipedia.org/wiki/Latvia">Latvia</a> */
    LV("Lettonie", "LVA", 428),

    /** <a href="http://en.wikipedia.org/wiki/Libya">Libya</a> */
    LY("Libye", "LBY", 434),

    /** <a href="http://en.wikipedia.org/wiki/Morocco">Morocco</a> */
    MA("Maroc", "MAR", 504),

    /** <a href="http://en.wikipedia.org/wiki/Monaco">Monaco</a> */
    MC("Monaco", "MCO", 492),

    /** <a href="http://en.wikipedia.org/wiki/Moldova">Republic of Moldova</a> */
    MD("Moldavie", "MDA", 498),

    /** <a href="http://en.wikipedia.org/wiki/Montenegro">Montenegro</a> */
    ME("Montenegro", "MNE", 499),

    /** <a href="http://en.wikipedia.org/wiki/Collectivity_of_Saint_Martin">Saint Martin (French part)</a> */
    MF("Saint Martin", "MAF", 663),

    /** <a href="http://en.wikipedia.org/wiki/Madagascar">Madagascar</a> */
    MG("Madagascar", "MDG",450),

    /** <a href="http://en.wikipedia.org/wiki/Marshall_Islands">Marshall Islands</a> */
    MH("Iles Marshall", "MHL", 584),

    /** <a href="http://en.wikipedia.org/wiki/Republic_of_Macedonia">The former Yugoslav Republic of Macedonia</a> */
    MK("Macedoine", "MKD", 807),

    /** <a href="http://en.wikipedia.org/wiki/Mali">Mali</a> */
    ML("Mali", "MLI", 466),

    /** <a href="http://en.wikipedia.org/wiki/Myanmar">Myanmar</a> */
    MM("Myanmar", "MMR", 104),

    /** <a href="http://en.wikipedia.org/wiki/Mongolia">Mongolia</a> */
    MN("Mongolia", "MNG", 496),

    /** <a href="http://en.wikipedia.org/wiki/Macau">Macao</a> */
    MO("Macao", "MAC", 446),

    /** <a href="http://en.wikipedia.org/wiki/Northern_Mariana_Islands">Northern Mariana Islands</a> */
    MP("Iles Mariannes du Nord", "MNP",580),

    /** <a href="http://en.wikipedia.org/wiki/Martinique">Martinique</a> */
    MQ("Martinique", "MTQ", 474),

    /** <a href="http://en.wikipedia.org/wiki/Mauritania">Mauritania</a> */
    MR("Mauritanie", "MRT", 478),

    /** <a href="http://en.wikipedia.org/wiki/Montserrat">Montserrat</a> */
    MS("Montserrat", "MSR", 500),

    /** <a href="http://en.wikipedia.org/wiki/Malta">Malta</a> */
    MT("Malte", "MLT", 470),

    /** <a href="http://en.wikipedia.org/wiki/Mauritius">Mauritius</a> */
    MU("Ile Maurice", "MUS", 480),

    /** <a href="http://en.wikipedia.org/wiki/Maldives">Maldives</a> */
    MV("Maldives", "MDV", 462),

    /** <a href="http://en.wikipedia.org/wiki/Malawi">Malawi</a> */
    MW("Malawi", "MWI", 454),

    /** <a href="http://en.wikipedia.org/wiki/Mexico">Mexico</a> */
    MX("Mexique", "MEX", 484),

    /** <a href="http://en.wikipedia.org/wiki/Malaysia">Malaysia</a> */
    MY("Malaysie", "MYS", 458),

    /** <a href="http://en.wikipedia.org/wiki/Mozambique">Mozambique</a> */
    MZ("Mozambique", "MOZ", 508),

    /** <a href="http://en.wikipedia.org/wiki/Namibia">Namibia</a> */
    NA("Namibie", "NAM", 516),

    /** <a href="http://en.wikipedia.org/wiki/New_Caledonia">New Caledonia</a> */
    NC("Nouvelle-Caledonie", "NCL", 540),

    /** <a href="http://en.wikipedia.org/wiki/Niger">Niger</a> */
    NE("Niger", "NER", 562),

    /** <a href="http://en.wikipedia.org/wiki/Norfolk_Island">Norfolk Island</a> */
    NF("Ile Norfolk", "NFK", 574),

    /** <a href="http://en.wikipedia.org/wiki/Nigeria">Nigeria</a> */
    NG("Nigeria","NGA", 566),

    /** <a href="http://en.wikipedia.org/wiki/Nicaragua">Nicaragua</a> */
    NI("Nicaragua", "NIC", 558),

    /** <a href="http://en.wikipedia.org/wiki/Netherlands">Netherlands</a> */
    NL("Pays-Bas", "NLD", 528),

    /** <a href="http://en.wikipedia.org/wiki/Norway">Norway</a> */
    NO("Norvege", "NOR", 578),

    /** <a href="http://en.wikipedia.org/wiki/Nepal">Nepal</a> */
    NP("Nepal", "NPL", 524),

    /** <a href="http://en.wikipedia.org/wiki/Nauru">Nauru</a> */
    NR("Nauru", "NRU", 520),

    /** <a href="http://en.wikipedia.org/wiki/Niue">Niue</a> */
    NU("Niue", "NIU", 570),

    /** <a href="http://en.wikipedia.org/wiki/New_Zealand">New Zealand</a> */
    NZ("Nouvelle Zelande", "NZL", 554),

    /** <a href=http://en.wikipedia.org/wiki/Oman"">Oman</a> */
    OM("Oman", "OMN", 512),

    /** <a href="http://en.wikipedia.org/wiki/Panama">Panama</a> */
    PA("Panama", "PAN", 591),

    /** <a href="http://en.wikipedia.org/wiki/Peru">Peru</a> */
    PE("Perou", "PER", 604),

    /** <a href="http://en.wikipedia.org/wiki/French_Polynesia">French Polynesia</a> */
    PF("Polynesie francaise", "PYF", 258),

    /** <a href="http://en.wikipedia.org/wiki/Papua_New_Guinea">Papua New Guinea</a> */
    PG("Papouasie-Nouvelle-Guinee", "PNG", 598),

    /** <a href="http://en.wikipedia.org/wiki/Philippines">Philippines</a> */
    PH("Philippines", "PHL", 608),

    /** <a href="http://en.wikipedia.org/wiki/Pakistan">Pakistan</a> */
    PK("Pakistan", "PAK", 586),

    /** <a href="http://en.wikipedia.org/wiki/Poland">Poland</a> */
    PL("Pologne", "POL", 616),

    /** <a href="http://en.wikipedia.org/wiki/Saint_Pierre_and_Miquelon">Saint Pierre and Miquelon</a> */
    PM("Saint Pierre et Miquelon", "SPM", 666),

    /** <a href="http://en.wikipedia.org/wiki/Pitcairn_Islands">Pitcairn</a> */
    PN("Iles Pitcairn", "PCN", 612),

    /** <a href="http://en.wikipedia.org/wiki/Puerto_Rico">Puerto Rico</a> */
    PR("Puerto Rico", "PRI", 630),

    /** <a href="http://en.wikipedia.org/wiki/Palestinian_territories">Occupied Palestinian Territory</a> */
    PS("Territoire Palestinien", "PSE", 275),

    /** <a href="http://en.wikipedia.org/wiki/Portugal">Portugal</a> */
    PT("Portugal", "PRT", 620),

    /** <a href="http://en.wikipedia.org/wiki/Palau">Palau</a> */
    PW("Palaos", "PLW", 585),

    /** <a href="http://en.wikipedia.org/wiki/Paraguay">Paraguay</a> */
    PY("Paraguay", "PRY", 600),

    /** <a href="http://en.wikipedia.org/wiki/Qatar">Qatar</a> */
    QA("Qatar", "QAT", 634),

    /** <a href="http://en.wikipedia.org/wiki/R%C3%A9union">R&eacute;union</a> */
    RE("R\u00E9union", "REU", 638),

    /** <a href="http://en.wikipedia.org/wiki/Romania">Romania</a> */
    RO("Romanie", "ROU", 642),

    /** <a href="http://en.wikipedia.org/wiki/Serbia">Serbia</a> */
    RS("Serbie", "SRB", 688),

    /** <a href="http://en.wikipedia.org/wiki/Russia">Russian Federation</a> */
    RU("Russie", "RUS", 643),

    /** <a href="http://en.wikipedia.org/wiki/Rwanda">Rwanda</a> */
    RW("Rwanda", "RWA", 646),

    /** <a href="http://en.wikipedia.org/wiki/Saudi_Arabia">Saudi Arabia</a> */
    SA("Arabie Saoudite", "SAU", 682),

    /** <a href="http://en.wikipedia.org/wiki/Solomon_Islands">Solomon Islands</a> */
    SB("Iles Salomon", "SLB", 90),

    /** <a href="http://en.wikipedia.org/wiki/Seychelles">Seychelles</a> */
    SC("Seychelles", "SYC", 690),

    /** <a href="http://en.wikipedia.org/wiki/Sudan">Sudan</a> */
    SD("Soudan", "SDN", 729),

    /** <a href="http://en.wikipedia.org/wiki/Sweden">Sweden</a> */
    SE("Suede", "SWE", 752),

    /** <a href="http://en.wikipedia.org/wiki/Singapore">Singapore</a> */
    SG("Singapore", "SGP", 702),

    /** <a href="http://en.wikipedia.org/wiki/Saint_Helena,_Ascension_and_Tristan_da_Cunha">Saint Helena, Ascension and Tristan da Cunha</a> */
    SH("Sainte Helene", "SHN", 654),

    /** <a href="http://en.wikipedia.org/wiki/Slovenia">Slovenia</a> */
    SI("Slovenie", "SVN", 705),

    /** <a href="http://en.wikipedia.org/wiki/Svalbard_and_Jan_Mayen">Svalbard and Jan Mayen</a> */
    SJ("Svalbard et Jan Mayen", "SJM", 744),

    /** <a href="http://en.wikipedia.org/wiki/Slovakia">Slovakia</a> */
    SK("Slovaquie", "SVK", 703),

    /** <a href="http://en.wikipedia.org/wiki/Sierra_Leone">Sierra Leone</a> */
    SL("Sierra Leone", "SLE", 694),

    /** <a href="http://en.wikipedia.org/wiki/San_Marino">San Marino</a> */
    SM("San Marin", "SMR", 674),

    /** <a href="http://en.wikipedia.org/wiki/Senegal">Senegal</a> */
    SN("Senegal", "SEN", 686),

    /** <a href="http://en.wikipedia.org/wiki/Somalia">Somalia</a> */
    SO("Somalie", "SOM", 706),

    /** <a href="http://en.wikipedia.org/wiki/Suriname">Suriname</a> */
    SR("Surinam", "SUR", 740),

    /** <a href="http://en.wikipedia.org/wiki/South_Sudan">South Sudan</a> */
    SS("Soudan du Sud", "SSD", 728),

    /** <a href="http://en.wikipedia.org/wiki/S%C3%A3o_Tom%C3%A9_and_Pr%C3%ADncipe">Sao Tome and Principe</a> */
    ST("Sao Tome-et-Principe", "STP", 678),

    /** <a href="http://en.wikipedia.org/wiki/El_Salvador">El Salvador</a> */
    SV("Le Salvador", "SLV", 222),

    /** <a href="http://en.wikipedia.org/wiki/Sint_Maarten">Sint Maarten (Dutch part)</a> */
    SX("Saint-Martin", "SXM", 534),

    /** <a href="http://en.wikipedia.org/wiki/Syria">Syrian Arab Republic</a> */
    SY("Syrie", "SYR", 760),

    /** <a href="http://en.wikipedia.org/wiki/Swaziland">Swaziland</a> */
    SZ("Swaziland", "SWZ", 748),

    /** <a href="http://en.wikipedia.org/wiki/Turks_and_Caicos_Islands">Turks and Caicos Islands</a> */
    TC("Iles Turques et Caiques", "TCA", 796),

    /** <a href="http://en.wikipedia.org/wiki/Chad">Chad</a> */
    TD("Tchad", "TCD", 148),

    /** <a href="http://en.wikipedia.org/wiki/French_Southern_and_Antarctic_Lands">French Southern Territories</a> */
    TF("Terres australes et antarctiques francaises", "ATF", 260),

    /** <a href="http://en.wikipedia.org/wiki/Togo">Togo</a> */
    TG("Togo", "TGO", 768),

    /** <a href="http://en.wikipedia.org/wiki/Thailand">Thailand</a> */
    TH("Thailande", "THA", 764),

    /** <a href="http://en.wikipedia.org/wiki/Tajikistan">Tajikistan</a> */
    TJ("Tajikistan", "TJK", 762),

    /** <a href="http://en.wikipedia.org/wiki/Tokelau">Tokelau</a> */
    TK("Tokelau", "TKL", 772),

    /** <a href="http://en.wikipedia.org/wiki/East_Timor">Timor-Leste</a> */
    TL("Timor oriental", "TLS", 626),

    /** <a href="http://en.wikipedia.org/wiki/Turkmenistan">Turkmenistan</a> */
    TM("Turkmenistan", "TKM", 795),

    /** <a href="http://en.wikipedia.org/wiki/Tunisia">Tunisia</a> */
    TN("Tunisie", "TUN", 788),

    /** <a href="http://en.wikipedia.org/wiki/Tonga">Tonga</a> */
    TO("Tonga", "TON", 776),

    /** <a href="http://en.wikipedia.org/wiki/Turkey">Turkey</a> */
    TR("Turquie", "TUR", 792),

    /** <a href="http://en.wikipedia.org/wiki/Trinidad_and_Tobago">Trinidad and Tobago</a> */
    TT("Trinite et Tobago", "TTO", 780),

    /** <a href="http://en.wikipedia.org/wiki/Tuvalu">Tuvalu</a> */
    TV("Tuvalu", "TUV", 798),

    /** <a href="http://en.wikipedia.org/wiki/Taiwan">Taiwan, Province of China</a> */
    TW("Taiwan", "TWN", 158),

    /** <a href="http://en.wikipedia.org/wiki/Tanzania">United Republic of Tanzania</a> */
    TZ("Tanzanie", "TZA", 834),

    /** <a href="http://en.wikipedia.org/wiki/Ukraine">Ukraine</a> */
    UA("Ukraine", "UKR", 804),

    /** <a href="http://en.wikipedia.org/wiki/Uganda">Uganda</a> */
    UG("Uganda", "UGA", 800),

    /** <a href="http://en.wikipedia.org/wiki/United_States_Minor_Outlying_Islands">United States Minor Outlying Islands</a> */
    UM("Iles Mineures eloignees des Etats-Unis", "UMI", 581),

    /** <a href="http://en.wikipedia.org/wiki/United_States">United States</a> */
    US("Etats-Unis (USA)", "USA", 840),

    /** <a href="http://en.wikipedia.org/wiki/Uruguay">Uruguay</a> */
    UY("Uruguay", "URY", 858),

    /** <a href="http://en.wikipedia.org/wiki/Uzbekistan">Uzbekistan</a> */
    UZ("Ouzbekistan", "UZB", 860),

    /** <a href="http://en.wikipedia.org/wiki/Vatican_City">Holy See (Vatican City State)</a> */
    VA("Vatican", "VAT", 336),

    /** <a href="http://en.wikipedia.org/wiki/Saint_Vincent_and_the_Grenadines">Saint Vincent and the Grenadines</a> */
    VC("Saint-Vincent-et-les-Grenadines", "VCT", 670),

    /** <a href="http://en.wikipedia.org/wiki/Venezuela">Bolivarian Republic of Venezuela</a> */
    VE("Venezuela", "VEN", 862),

    /** <a href="http://en.wikipedia.org/wiki/British_Virgin_Islands">British Virgin Islands</a> */
    VG("Iles Vierges britanniques", "VGB", 92),

    /** <a href="http://en.wikipedia.org/wiki/United_States_Virgin_Islands">Virgin Islands, U.S.</a> */
    VI("Iles Vierges des Etats-Unis", "VIR", 850),

    /** <a href="http://en.wikipedia.org/wiki/Vietnam">Viet Nam</a> */
    VN("Vietnam", "VNM", 704),

    /** <a href="http://en.wikipedia.org/wiki/Vanuatu">Vanuatu</a> */
    VU("Vanuatu", "VUT", 548),

    /** <a href="http://en.wikipedia.org/wiki/Wallis_and_Futuna">Wallis and Futuna</a> */
    WF("Wallis et Futuna", "WLF", 876),

    /** <a href="http://en.wikipedia.org/wiki/Samoa">Samoa</a> */
    WS("Samoa", "WSM", 882),

    /** <a href="http://en.wikipedia.org/wiki/kosovo">Kosovo</a> */
    XK("Kosovo", "XXK", 383),
    
    /** <a href="http://en.wikipedia.org/wiki/Yemen">Yemen</a> */
    YE("Yemen", "YEM", 887),

    /** <a href="http://en.wikipedia.org/wiki/Mayotte">Mayotte</a> */
    YT("Mayotte", "MYT", 175),

    /** <a href="http://en.wikipedia.org/wiki/South_Africa">South Africa</a> */
    ZA("Afrique du Sud", "ZAF", 710),

    /** <a href="http://en.wikipedia.org/wiki/Zambia">Zambia</a> */
    ZM("Zambie", "ZMB", 894),

    /** <a href="http://en.wikipedia.org/wiki/Zimbabwe">Zimbabwe</a> */
    ZW("Zimbabwe", "ZWE", 716),
    ;
    // @formatter:on


    private static final Map<String, CountryCode> alpha3Map = new HashMap<String, CountryCode>();
    private static final Map<Integer, CountryCode> numericMap = new HashMap<Integer, CountryCode>();


    static
    {
        for (CountryCode cc : values())
        {
            alpha3Map.put(cc.getAlpha3(), cc);
            numericMap.put(cc.getNumeric(), cc);
        }
    }


    private final String name;
    private final String alpha3;
    private final int numeric;


    private CountryCode(String name, String alpha3, int numeric)
    {
        this.name = name;
        this.alpha3 = alpha3;
        this.numeric = numeric;
    }


    /**
     * Get the country name.
     *
     * @return
     *         The country name.
     */
    public String getName()
    {
        return name;
    }


    /**
     * Get the <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2"
     * >ISO 3166-1 alpha-2</a> code.
     *
     * @return
     *         The <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2"
     *         >ISO 3166-1 alpha-2</a> code.
     */
    public String getAlpha2()
    {
        return name();
    }


    /**
     * Get the <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3"
     * >ISO 3166-1 alpha-3</a> code.
     *
     * @return
     *         The <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3"
     *         >ISO 3166-1 alpha-3</a> code.
     */
    public String getAlpha3()
    {
        return alpha3;
    }


    /**
     * Get the <a href="http://en.wikipedia.org/wiki/ISO_3166-1_numeric"
     * >ISO 3166-1 numeric</a> code.
     *
     * @return
     *         The <a href="http://en.wikipedia.org/wiki/ISO_3166-1_numeric"
     *         >ISO 3166-1 numeric</a> code.
     */
    public int getNumeric()
    {
        return numeric;
    }


    /**
     * Get a CountryCode that corresponds to a given ISO 3166-1
     * <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">alpha-2</a> or
     * <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3">alpha-3</a> code.
     *
     * @param code
     *         An ISO 3166-1 <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2"
     *         >alpha-2</a> or <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3"
     *         >alpha-3</a> code.
     *
     * @return
     *         A CountryCode instance, or null if not found.
     */
    public static CountryCode getByCode(String code)
    {
        if (code == null)
        {
            return null;
        }

        switch (code.length())
        {
            case 2:
                return getByAlpha2Code(code);

            case 3:
                return getByAlpha3Code(code);

            default:
                return null;
        }
    }


    private static CountryCode getByAlpha2Code(String code)
    {
        try
        {
            return Enum.valueOf(CountryCode.class, code);
        }
        catch (IllegalArgumentException e)
        {
            return null;
        }
    }


    private static CountryCode getByAlpha3Code(String code)
    {
        return alpha3Map.get(code);
    }


    /**
     * Get a CountryCode that corresponds to a given
     * <a href="http://en.wikipedia.org/wiki/ISO_3166-1_numeric">ISO 3166-1
     * numeric</a> code.
     *
     * @param code
     *         An <a href="http://en.wikipedia.org/wiki/ISO_3166-1_numeric"
     *         >ISO 3166-1 numeric</a> code.
     *
     * @return
     *         A CountryCode instance, or null if not found.
     */
    public static CountryCode getByCode(int code)
    {
        return numericMap.get(code);
    }
}
