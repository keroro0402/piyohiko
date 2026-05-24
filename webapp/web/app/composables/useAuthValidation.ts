import { toTypedSchema } from '@vee-validate/zod';
import * as z from 'zod';
import { TEXT } from '~/constants/text';

export const useAuthValidation = () => {
  const loginSchema = toTypedSchema(
    z.object({
      loginId: z.string().min(1, TEXT.FORM.ERROR_REQUIRED_LOGIN_ID),
      password: z.string().min(1, TEXT.FORM.ERROR_REQUIRED_PASSWORD),
    }),
  );
  const registerSchema = toTypedSchema(
    z.object({
      loginId: z.string().min(1, TEXT.FORM.ERROR_REQUIRED_LOGIN_ID),
      password: z.string().min(1, TEXT.FORM.ERROR_REQUIRED_PASSWORD),
      confirmPassword: z.string().min(1, TEXT.REGISTER.ERROR_REQUIRED_CONFIRM_PASSWORD),
    }),
  );

  return {
    loginSchema,
    registerSchema,
  };
};
