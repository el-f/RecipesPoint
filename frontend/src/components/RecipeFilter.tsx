import { useAtom } from "jotai";
import { queryAtom } from "../atoms/query-atom";
import * as Yup from "yup";
import { Form, FormikProvider, useFormik } from "formik";
import { Button, Stack, TextField, Tooltip } from "@mui/material";
import { dietAsSelectOptions } from "../@types/diet.enum";
import { cuisineAsSelectOptions } from "../@types/cuisine.enum";
import { categoryAsSelectOptions } from "../@types/category.enum";
import useRecipes from "../api/use-recipes";
import SearchIcon from "@mui/icons-material/Search";

export default function RecipeFilter() {
  const [query, setQuery] = useAtom(queryAtom);
  const { isLoading, error } = useRecipes(query);

  const querySchema = Yup.object().shape({
    diet: Yup.string().optional().nullable(),
    cuisine: Yup.string().optional().nullable(),
    category: Yup.string().optional().nullable(),
    freeText: Yup.string().optional().nullable(),
  });

  const formik = useFormik({
    initialValues: {
      diet: query.diet || "",
      cuisine: query.cuisine || "",
      category: query.category || "",
      freeText: query.freeText || "",
    },
    validationSchema: querySchema,
    onSubmit: (values) => {
      setQuery({
        ...query,
        diet: values.diet || null,
        cuisine: values.cuisine || null,
        category: values.category || null,
        freeText: values.freeText || null,
      });
      setSubmitting(false);
    },
  });

  const { errors, touched, handleSubmit, isSubmitting, getFieldProps, setSubmitting } = formik;

  return (
    <FormikProvider value={formik}>
      <Form autoComplete="off" noValidate onSubmit={handleSubmit}>
        <Stack direction="column" spacing={2}>
          <TextField
            fullWidth
            label="Cuisine"
            {...getFieldProps("cuisine")}
            select
            SelectProps={{ native: true }}
            error={Boolean(touched.cuisine && errors.cuisine)}
            helperText={touched.cuisine && errors.cuisine}
          >
            <option value="" />
            {cuisineAsSelectOptions.map((cuisine) => (
              <option key={cuisine.value} value={cuisine.value}>
                {cuisine.label}
              </option>
            ))}
          </TextField>
          <TextField
            fullWidth
            label="Diet"
            {...getFieldProps("diet")}
            select
            SelectProps={{ native: true }}
            error={Boolean(touched.diet && errors.diet)}
            helperText={touched.diet && errors.diet}
          >
            <option value="" />
            {dietAsSelectOptions.map((diet) => (
              <option key={diet.value} value={diet.value}>
                {diet.label}
              </option>
            ))}
          </TextField>
          <TextField
            fullWidth
            label="Category"
            {...getFieldProps("category")}
            select
            SelectProps={{ native: true }}
            error={Boolean(touched.category && errors.category)}
            helperText={touched.category && errors.category}
          >
            <option value="" />
            {categoryAsSelectOptions.map((cuisine) => (
              <option key={cuisine.value} value={cuisine.value}>
                {cuisine.label}
              </option>
            ))}
          </TextField>
          <TextField
            fullWidth
            label="Free Text"
            {...getFieldProps("freeText")}
            error={Boolean(touched.freeText && errors.freeText)}
            helperText={touched.freeText && errors.freeText}
          />
          <Tooltip title="Search">
            <span>
              <Button
                type="submit"
                variant="contained"
                fullWidth
                style={{ backgroundColor: "#5E81AC", color: "white" }}
                startIcon={<SearchIcon />}
                disabled={isSubmitting || isLoading || !!error}
              >
                Search
              </Button>
            </span>
          </Tooltip>
        </Stack>
      </Form>
    </FormikProvider>
  );
}
